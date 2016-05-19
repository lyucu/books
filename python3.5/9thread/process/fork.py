#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

'fork'

__author__ = 'Markus Liu'

################# fork
print('##########  fork')
import os
print('process' , os.getpid())
pid = os.fork()
print('pid=' ,pid)
if pid == 0 :
	print(os.getpid() , ' and1 ' , os.getppid())
else :
	print(os.getpid() , 'and2 ' , pid) 	



