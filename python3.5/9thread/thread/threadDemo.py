#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

'thread demo'

__author__ = 'Markus Liu'

##################### thread demo
print('############## tread demo')

import time , threading
def loop():
	print('thread is runnig for %s' %(threading.current_thread().name))
	n = 0
	while n < 5 :
		n = n + 1
		print('thread %s >> %s' %(threading.current_thread().name, n))
		time.sleep(1)
	print('thread is end for %s' %(threading.current_thread().name))
print('start demo for %s' %(threading.current_thread().name))
t = threading.Thread(target=loop,name='loopThread')
t.start()
t.join()
print('end demo')	

