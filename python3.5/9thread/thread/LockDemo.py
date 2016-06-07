#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

'Lock demo'

__author__ = 'Markus Liu'

##################### thread demo
print('############## Lock demo')

import time , threading
balance = 0
lock = threading.Lock()

def changeValue(n) :
	global balance
	balance = balance + n
	balance = balance - n

def run(n) :
	for i in range(200000) :
		lock.acquire()
		try:
			changeValue(n)
		finally:
			lock.release()
t1 = threading.Thread(target=run , args=(3,))
t2 = threading.Thread(target=run , args=(9,))
t1.start()
t2.start()
t1.join()
t2.join()
print(balance)		
