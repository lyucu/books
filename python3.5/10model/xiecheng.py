#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'xiecheng'

__author__ = 'Markus Liu'

print('xiecheng  demo')

import time
def consumer() :
	r = ''
	while True :
		n = yield time.sleep(1)
		if not n :
			return
		print('consumer ')
		
def	producer(c):
	c.send(None)
	i = 0
	while i<5:
		i= i+1
		print('producer')
		c.send(i)
	c.close()
c = consumer()
producer(c)

