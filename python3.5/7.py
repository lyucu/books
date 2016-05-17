#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

' type , metaclass, try , log , raise,assert， pdb'
'unit test'

__author__ = 'Markus Liu'

################# type
def fn(self , name='value') :
	print('hellow',name)
Hello = type('Hello' , (object,), dict(pt=fn))
hel = Hello();
hel.pt()	

############# metaclass to do


################# try
print('################try')
try:
	print('try')
	i  = 10 / 10
except Exception as e:
	print(e)
else :
	print('slse')	
finally:
	print('finally')

################# log
print('################log')
import logging
logging.basicConfig(level=logging.INFO)
logging.info('123')
try:
	i = 10 / 0 
except Exception as e:
	logging.exception(e)
finally:
	pass

############# raise exception
print('########## raise exception')

class MyException(Exception) :
	pass
def thrE() :
	raise MyException('my except');

try:
		thrE()
except MyException as e:
	print('catch' ,e)
	# raise
else:
	pass
finally:
	pass	
############# assert 
assert 1 == 1	

######### pdb
import pdb
dd = 'dd'
# pdb.set_trace()

########## unit test
class For(object) :
	def pt(self) :
		return "123"
import unittest
class TestUnit(unittest.TestCase) :
	def test_pt(self) :
		print("test pt")
		self.assertEqual(1,1)
		d = {}
		with self.assertRaises(KeyError):
			value = d['empty']
	def setUp(self) :
		print('haha set up')
	def  tearDown(self):
		print('haha tearDown')			
if __name__ == '__main__':
    unittest.main()


		

