#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'process communicate'

__author__ = 'Markus Liu'

############# process communcate 
print('###### process communcate')
from multiprocessing import Process , Queue
import os , random , time

def write(q) :
	print('process to wirte (%s) ' %(os.getpid()))
	for value in ['A' , 'B' , 'C']:
		print('put ' , value)
		q.put(value)
		time.sleep(random.random())
def read(q):
	print('process to read (%s)' %(os.getpid()) )
	while True :
		value = q.get(True)
		print('get %s from queue' %(value))

if __name__ == '__main__' :
	q = Queue()
	pw = Process(target=write , args=(q,))
	pr = Process(target=read , args=(q,))
	pw.start()
	pr.start()
	pw.join()
	pr.terminate()		
