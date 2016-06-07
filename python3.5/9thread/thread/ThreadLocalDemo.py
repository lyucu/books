#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

'threadlocal demo'

__author__ = 'Markus Liu'

##################### threadlocal demo
print('############## threadlocal demo')
import threading

local_school = threading.local()

def processStudents() :
	std = local_school.student
	print('%s , %s'  %(std ,threading.current_thread().name))
def processThread(name) :
	local_school.student = name
	processStudents()
t1 = threading.Thread(target=processThread , args=('alies',),name='Thread a')
t2 = threading.Thread(target=processThread,args = ('Boy',), name='Thread b')
t1.start()
t2.start()
t1.join()
t2.join()