#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'file operation '
'read file read readline readlines read binary encoding'
'write file'
'memmary read, memmary write'
'os system'
'file opreation'
'serialization'
'json'

__author__ = 'Markus Liu'


################ read file
f = open('/Users/ycliu/tmp/testfile.txt')
content = f.read()
print(content)
f.close();

try:
	f = open('/Users/ycliu/tmp/testfile.txt')
	content = f.read()
finally:
	if f :
		f.close()

with open('/Users/ycliu/tmp/testfile.txt', 'r') as f:
	f.read() 

print("############ read size")
with open('/Users/ycliu/tmp/testfile.txt', 'r') as f:
	print(f.read(10))
	f.readline()

print('##########   read lines')	
with open('/Users/ycliu/tmp/testfile.txt', 'r') as f:
	for line in f.readlines() :
		print(line.strip())

print('##########   encoding')
with open('/Users/ycliu/tmp/1.txt', 'r',encoding='UTF-8', errors='ignore') as f:
	print(f.read(1024))

############## write file
print('######### wirte file')
with open ('/Users/ycliu/tmp/out.txt', 'w') as f:
	f.write('out put')

############# mm read
print('########## mm string')
from io import StringIO
f = StringIO()
f.write('s')
print(f.getvalue())
f = StringIO('12\n312fdf\nafads')
v = f.readlines();
print(v)

print('######### mm byte')
from io import BytesIO
f = BytesIO();
f.write('中文'.encode('utf-8'))
print(f.getvalue())

f  = BytesIO('heh'.encode('utf-8'))
print(f.read())

############## os funtion
print('############## os')
import os
print(os.name)
print(os.uname())
print(os.environ)
print(os.environ.get('USER'))

############## file opreation
print(os.path.abspath('.'))
p = os.path.join('/Users/ycliu/tmp/' , 'testdir')
print(p)
os.mkdir(p)
os.rmdir(p)
print(os.path.split('/Users/michael/testdir/file.txt'))
print(os.path.splitext('/Users/michael/testdir/file.txt'))
os.rename('/Users/ycliu/tmp/out.txt','/Users/ycliu/tmp/out1.txt')
os.remove('/Users/ycliu/tmp/out1.txt')

v = [x for x in os.listdir('/Users/ycliu/tmp') if os.path.isdir('/Users/ycliu/tmp/' + x)]
print(v)

##################serialization
print('###########serialization')
import pickle
d = {'ke1' : 'v1' , 'k2' : 'v2'}
d = pickle.dumps(d)
print(d)
with open ('/Users/ycliu/tmp/ser.txt' , 'wb') as f:
	f.write(d)
d = {'ke1' : 'v1' , 'k2' : 'v2'}	
with open ('/Users/ycliu/tmp/ser.txt' , 'wb') as f:
	pickle.dump(d, f)
with open ('/Users/ycliu/tmp/ser.txt' , 'rb') as f:	
	d = pickle.load(f)
print(d)
d = pickle.dumps(d)
d = pickle.loads(d)
print(d) 

################# json
print('########## json')
import json
d = {'k1' : 'v1' , 'k2' : 'v2'}
d = json.dumps(d)
print(d)
d = json.loads(d)
print(d)

class Student(object) :
	def __init__(self, name , age) :
		self.name = name
		self.age = age
s = Student('Bb' , 11)
def s2disc(stu) :
	return {'name' : stu.name , 'age' : stu.age}

d = json.dumps(s , default=s2disc)		
print(d)
print(json.dumps(s , default = lambda ob: ob.__dict__))

def disc2s(disc) :
	print(disc)
	return Student(disc['name'] , disc['age'])
print(json.loads(d , object_hook=disc2s))	






		