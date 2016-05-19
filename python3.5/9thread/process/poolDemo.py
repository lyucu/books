#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'process pool'

__author__ = 'Markus Liu'

############# process pool
from multiprocessing import Pool
import os , time , random

def run1(name) :
	print('run task for %s (%s)' % (name , os.getpid()))
	start = time.time()
	time.sleep(random.random()*3)
	end = time.time()
	print('task  %s run for %0.2f' % (name ,end - start))

if __name__ == '__main__' :
	print('Parent Process ' , os.getpid())
	p = Pool(4)
	for i in range(5) :
		p.apply_async(run1, args=(i,))
	p.close()
	p.join()
	print('Finish Parent')		