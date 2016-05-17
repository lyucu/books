#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

' comments '

__author__ = 'Markus Liu'

import sys

def enter() :
	print(sys.argv)


if (__name__ == '__main__') :
	enter();

########### value scope
print('######### value scope')	

def _inMethod() :
	print('this is innert')
_innerValue = '123'

import forImp
print(forImp.value)

########## class
print('########### class')
class Student(object):
	pass
_myStudent = Student();
print(_myStudent)
_myStudent.name = '123'
print(_myStudent.name)
print(Student)	

class Student(object) :
	def __init__(self,name , age) :
		self.name = name
		self.age = age
	def toPt(self) :
		return self.name , self.age	
_myStudent = Student('nae1' , '18')	
print(_myStudent.name , _myStudent.age)	
print(_myStudent.toPt())

###############private value
print('########## private ')
class Student(object) :
	def __init__(self,name , age) :
		self._name = name
		self._age = age
	def getName(self) :
		return self._name
	def setName(self, name) :
		 self._name = name		
_myStudent = Student('nae1' , '18')	
print(_myStudent.getName())
_myStudent.setName("name2")
print(_myStudent.getName())	

############### inherit
print('########## inherit')
class Animal(object) :
	def run(self) :
		print('Animal run')
class Dog(Animal) :
	def run(self) :
		print('Dog run')
class Cat(Animal) :
	def run(self) :
		print('Cat run')
class Fruit(object) :
	def run(self) :
		print('Fruit run')		
dog = Dog();
dog.run();
cat = Cat();
cat.run();		

def tt(animal ) :
	animal.run()
tt(dog)		
tt	(Fruit())	

################ object info
print('########## object info')
print(type('123'))
print(type(dog))

import types
print(types)
def fen():
	pass
print(type(fen) == types.FunctionType)	
print(type(fen))
print(isinstance(dog , Animal))
print(isinstance(dog, Dog))
print(isinstance(Animal(),(Dog,Cat)))

print(dir(str))
class De(object) :
	def __init__(self ) :
		self._x = 1;
		self.y = 2;
	def __len__ (self):
		return 111;
d = De()		
print(len(d))
print(hasattr(De() , '_x'))	
print(hasattr(d , 'y'))
setattr(d , '_x' , 10)
print(getattr(d , '_x'))
print(getattr(d , 'z' , 'z'))
print(getattr(d , '__len__'))
fn = getattr(d , '__len__')
print(fn())

########### class value
print('###### class value')
class SSS(object):
	name = 'sss'
	def __init__(self):
		pass
s1 = SSS()	
s2 = SSS();
print(s1.name)
print(s2.name)	
s1.name = 's1'
print(s1.name)
print(s2.name)
