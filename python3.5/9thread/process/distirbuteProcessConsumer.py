#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

'distributePorcess 分布式进程 demo'

__author__ = 'Markus Liu'

##################### thread demo
print('############## distributePorcess demo')

import time , sys, queue
from multiprocessing.managers import BaseManager

class QueueManager(BaseManager) :
	pass

QueueManager.register('getTaskQueue')
QueueManager.register('getReslutQueue')
server_add = '127.0.0.1'
print('connect to server')
manager = QueueManager(address=(server_add,5000),authkey=b'abc')
manager.connect()

task=manager.getTaskQueue()
result=manager.getReslutQueue()

for i in range(10) :
	n = task.get(timeout=10)
	print('run task' , n)
	time.sleep(1)
	result.put(n-1)
print('exit')	

