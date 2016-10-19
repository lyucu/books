#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

__author__ = 'Markus Liu'

import redis
import time

print('start')
conn=redis.StrictRedis(host='localhost', port=6379, db=0)
LIMIT = 1000000

conn.flushdb()

def check_token(conn , tokne) :
	return conn.hget('login:' , tokne)

def	update_token(conn , token , user , itme=None) :
	now = time.time()
	print('add new user ' , user)
	conn.hset('login:' , token, user)
	conn.zadd('recent:' , now , token)
	if item :
		print('add item ' , item )
		conn.zadd('viewed:' + user , now , item)
## 删除25之后的数据	
		conn.zremrangebyrank('viewed:' + user , 0 , -26)
		conn.zincrby('viewed' , item , -1)

def clean_token(conn ) :
## 统计数量	
	size = conn.zcard('recent:')
	print('current has ' , size , 'token')
	if size < LIMIT :
		return
	end_index = min(size - LIMIT , 100)
## 将数据排序	
	deleteTokens = zrange('recent:' , 0 , end_index - 1)
	print('these token need to be delete ' , deleteTokens)
	token_key = []
	for token in deleteTokens :
		token_key.append('viewed:' + token)
		token_key.append('cart:' + token)
	conn.delete( *token_key)
	conn.hdel('login:' , *deleteTokens)
	conn.zrem('recent' , *deleteTokens)

def add_to_cart(conn , user , item , count) :
	if count <= 0 :
		print('remove itme ' , item , 'for user ' , user )
		conn.hdel('cart:' + user , item)
	else :
		print('add itme ' , item , ' ',count, 'for user ' , user )
		conn.hset('cart:' + user , item , count)	

def cache_request (conn , request) :
	pass

def cache_data_schedule (conn , row_id , delay) :
	print(time.time())
	conn.zadd('delay' , delay , row_id)
	conn.zadd('schedule' , time.time() , row_id)	
def cache_data (conn , row_id) :
## 查询数据	
	next = conn.zrange('schedule' , 0 , 0 , withscores=True)
	now = time.time()
	print(next)
	if not next or next[0][1] > now :
		pass
	row = next[0][0]
## 查询得分	
	delay = conn.zscore('delay' , row)
	if delay < 0 :
		conn.zrem('delay' , row)
		conn.zrem('schedule' , row)
		conn.delete('value:' + row)
		pass
	else :
		print('do query')	
		conn.set('value:' + row_id , 1)
		conn.zadd('schedule' ,  now + delay,row_id)

def prepare_viewed(conn ) :
## 删除 20000之后的数据	
	conn.zremrangebyrank('viewed' , 0 , -20001)
## 数量减半			
	conn.zinterstore('viewed' , {'viewed' : .5})
	
token = 'token1'
user = 'user1'
item = 'milk'
print('user1 longin ')
update_token(conn , token ,user ,item )	
check_token(conn , token)
clean_token(conn)
add_to_cart(conn , user , 'milk' , 1)
add_to_cart(conn , user , 'milk' , 0)
cache_request(conn , None)
cache_data_schedule(conn, 'row_1' ,10 )
cache_data(conn , 'row_1')