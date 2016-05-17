#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

' comments '

__author__ = 'Markus Liu'

############ conbine method
class Student(object) :
	pass

def say(self ,para) :
	print(self)
	print(para)

from types import MethodType
s = Student();
s.say = MethodType(say ,s );
s.say(123)

def cry (self , para) :
	print(para)
Student.cry = MethodType(cry , s)
s = Student()
s.cry('cry')	

#############__slots__
print('###########__slots__')
class Student(object) :
	__slots__ = ('name' , 'age')
s = Student();
s.name = '123'

#############@property
print('########@property')
class Student(object) :
	@property
	def name(self) :
		return self._myname;

	@name.setter
	def name(self, para) :
		self._myname = para	
	@property
	def age(self) :
		return 190
s = Student()
s.name = 1111;
print(s.name)
print(s.age)

############## multi inherit
class Fly(object) :
	def fly(self) :
		print('fly')
class Run(object) :
	def run(self) :
		print('run')
class Cat(Fly , Run) :
	pass
cat = Cat();
cat.fly();
cat.run();

############ class function
print('###########class function')	
class Stundent(object) :
	def __str__ (slef) :
		return ('customer print')
	__repr__ = __str__	
print(Stundent())	
Stundent()	
print('##########iterator')
class Fib(object) :
	def __init__(self ):
		self.a ,self.b= 0,1 
	def __iter__(self) :
		return self
	def __next__(self) :
		self.a,self.b = self.b,self.a + self.b
		if (self.a > 1000) :
			raise StopIteration();
		return self.a	
	def __getitem__(self , n) :
		if isinstance(n, int):
			for x in range(n) :
				self.a,self.b = self.b,self.a + self.b
			return self.a	
		if isinstance(n, slice):
			start = n.start
			stop = n.stop
			if start is None:
				start = 0
			L = []
			for x in range(stop) :
				if x > start:
					L.append(self.a)
				self.a,self.b = self.b,self.a + self.b	
			return L
for n in Fib():
	print(n)
print(Fib()[9])
print(Fib()[0:5])

print('__getattr__')
class DD (object) :
	def __getattr__(self , attr) :
		if attr == 'age' :
			return 1;
		if attr == 'name' :
			return compute;
	def compute(self) :
		return 2	
	def __call__(self) :
		print('this is used by call')			

d = DD()
print(d.age)
print(d.compute())
d()
print(callable('str'))
print(callable(d))

################# enum
print('######### enum')

from enum import Enum

Month = Enum('Mon' , ('J' , 'F' , 'M', 'A'))
Month.J
print(Month.J)
for name , value in Month.__members__.items() :
	print(name , value , value.value)

from enum import Enum, unique
@unique
class Weekday(Enum) :
	Sun = 0
	Mon = 1
	Thu = 2
	Wen = 3
	Thi = 4
	Fri = 5
	Sat = 6	
print(Weekday.Sun)
print(Weekday['Mon'])
print(Weekday.Wen.value)
print(Weekday(4))