#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

'multiprocessing'

__author__ = 'Markus Liu'

################# multiprocessing
print("########## multiprocessing")
from multiprocessing import Process
import os
def runP(name) :
	print("process child pid %s  nam %s " % (os.getpid() ,name ))	

if __name__ == '__main__' :
	print('Parent process ' , os.getpid())
	p = Process(target=runP,args=('test',))
	print('Child start')
	p.start()
	p.join()
	print('Child end')