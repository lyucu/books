#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

'distributePorcess 分布式进程 demo'

__author__ = 'Markus Liu'

##################### thread demo
print('############## distributePorcess demo')
import random,time , queue
from multiprocessing.managers import BaseManager

taskQueue = queue.Queue()
resultQueue = queue.Queue()

class QueueManger(BaseManager) :
	pass

QueueManger.register('getTaskQueue' , callable=lambda: taskQueue)
QueueManger.register('getReslutQueue',callable=lambda: resultQueue)

manger = QueueManger(address=('',5000),authkey=b'abc')
manger.start()

task=manger.getTaskQueue()
result=manger.getReslutQueue()

for i in range(10) :
	n =random.randint(0,100)
	task.put(n)
	print('put ' , n)

print('try get result')
for i in range(10) :
	r= result.get(timeout=10)
	print('get ' , r)
manger.shutdown()	

print('end')



