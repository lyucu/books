#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

###########function program
############map reduce filter sort
print('#############map reduce')
f = abs
print(f)

def myabs(x, y , f) :
	return f(x) , f(y)

print(myabs(-1,-2,abs))	

def qu(x) :
	return x * x
t = list(map(qu,[1,2,3,4]))
print(t)	

from functools import reduce
def pin(x ,y) :
	return x* 10 + y
t = reduce(pin,[1,3,4])	
print(t)

def normalName(x) :
	return x[0].upper() + x[1:].lower()
print(normalName('fas'))
L1 = ['adam', 'LISA', 'barT']
L2 = list(map(normalName, L1))
print(L2)	

def prod(L):
	def multi(x , y):
		return x * y
	return reduce(multi, L)
print('3 * 5 * 7 * 9 =', prod([3, 5, 7, 9]))		

def str2float(s):
	def chan(x) :
		return {'0': 0, '1': 1, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9 , '.':None}[x]
	result = list(map(chan,s))	
	def com(x ,y):
		if (y != None) :
			return x *10 + y
		else :
			return x;	

	return reduce(com, result)/10**(len(s)-s.index('.') -1)

print('str2float(\'123.45601\') =', str2float('123.45601'))

def isOdd(x) :
	return x %2 == 1
print(list(filter(isOdd , [1,2,3])))


L = [('Bob', 75), ('Adam', 92), ('Bart', 66), ('Lisa', 88)]

def by_name(t):
	return t[0].lower()

L2 = sorted(L, key=by_name,reverse=True)
print(L2)	

##############return function
print('#########return function')
def lazy_sum(*list) :
	def sum(x , y) :
		return x + y;
	def result() :
		return reduce(sum , list)
	return result;
f = lazy_sum(1,2,3)
print(f())	

def count():
    fs = []
    for i in range(1, 4):
        def f():
             return i*i
        fs.append(f)
    return fs
f1, f2, f3 = count()
print(f1(),f2(),f3())

def count1():
	fs = []
	def g(j) :
		def f():
			return j*j
		return f;	
	for i in range(1,4):
		fs.append(g(i))
	return fs;
f1, f2, f3 = count1()
print(f1(),f2(),f3())		

###########lambda
print('############ lambda')
s = list(map(lambda x: x*x , [1,2,3]))
print(s)
s = lambda x : x*x
print(s)
def count2():
	fs = [];
	def g(j) :
		return lambda  : j*j
	for i in range(1,4):
		fs.append(g(i))
	return fs;
f1, f2, f3 = count2()
print(f1(),f2(),f3())

#############deractor
print('######### deractor')
def tem() :
	pass
print(tem.__name__)	

def log(func) :
	def wap(*args , **kw) :
		print(func.__name__)
		return func(*args, **kw)
	return wap
@log
def now() :
	print('this is now')	
now()		
now = log(now)
now()

print('########## deractor 2')
def log(text) :
	def deractor(func) :
		def wap(*args , **kw) :
			print('this is %s %s ' %(text , func.__name__))
			print(*args , **kw)
			return func(*args , **kw)
		return wap
	return deractor	
@log('xxx')
def now(*su) :
	print('2015',*su)	
print(now.__name__)	
now(123)
now = log('xx')(now)
now(456)	

print('######## deractor copy function name')
import functools
def log(func) :
	@functools.wraps(func)
	def wap(*args, **kw) :
		print('%s ', func.__name__)
		return func(*args , **kw)
	return wap
@log
def now () :
	print('helow')
print(now.__name__)	
now()

print('####### test')
def log_t(*text) :
	def deractor (func) :
		def wap(*args, **kw) :
			print('before call')
			result = func(*args, ** kw)
			print('after call')
			return result
		return wap	
	return deractor 
@log_t('123')
def no1() :
	print('no1')
no1();	

