#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

__author__ = 'Markus Liu'

import redis
import time

print('start')
conn=redis.StrictRedis(host='localhost', port=6379, db=0)
ONE_WEEK_IN_SECONDS = 7*86400
VOTE_SCORE = 432
ARTICLE_PRE_PAGES = 25

# 清空数据
conn.flushdb()

def postArticle(conn , user , title , link) :
## 自增长	
	article_id = str(conn.incr('article_id'))
	voted = 'voted:' + article_id
## set 	
	conn.sadd(voted,user)
## 过期时间
	conn.expire(voted,ONE_WEEK_IN_SECONDS)
	now = time.time()
	article = 'article:' + article_id
## hash	
	conn.hmset(article,{
			'title' : title,
			'link' : link,
			'user' : user,
			'time' : now,
			'votes' : 1,
		})
## 有序集合	
	conn.zadd('score:', now + VOTE_SCORE ,article )
	conn.zadd('time:',  now, article)
	return article_id

def voteArticle(conn , user , article) :
	cutoff = time.time() - ONE_WEEK_IN_SECONDS
	if conn.zscore('time:',article) < cutoff:
		return
	article_id = article.partition(':')[-1]
	if conn.sadd('voted:' + article_id , user) :
## 有序集合自增长		
		conn.zincrby('score:' , article,VOTE_SCORE)
## hash自增长		
		conn.hincrby(article,'votes' , 1)

def getArticle(conn , page, order='score:') :
	start = (page - 1 ) * ARTICLE_PRE_PAGES
	end = start + ARTICLE_PRE_PAGES - 1
## 有序集合排序	
	ids = conn.zrevrange(order , start , end)
	artciles = []
	for id in ids :
		article_data = conn.hgetall(id)
		article_data["id"] = id
		artciles.append(article_data)
	return artciles

def addremoveGroup(conn , article_id, toAdd=[] , toRemove=[]) :
	article = 'article:' + article_id
	for group in toAdd :
		conn.sadd('group:' + group , article )
	for group in toRemove :
		conn.srem('group:' + group , article)

def getGroupArticle(conn , group , page , order='score:'):
	key = order + group
	if not conn.exists(key) :
#交叉两个 set 取交集				
		conn.zinterstore(key, ['group:'+group , order],aggregate='max',)
		conn.expire(key, 60)
	return getArticle(conn, page , key)	


id = postArticle(conn,'lyc1','title1','www.tfd')
print('crreate article for id ' , id)
id = postArticle(conn,'lyc2','title2','www.tfd.fda')
print('crreate article for id ' , id)
voteArticle(conn , 'user1' , 'article:1')
print('vote article 1')
articles = getArticle(conn , 1 )
print('get articles')
print(articles)