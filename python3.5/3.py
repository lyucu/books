#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

def pt(x):
	if isinstance(x , (int,float)) :
		print(x)

pt(123)	

def multialue(x,y):
	return x+1, y+1
x,y = multialue(1,2)
print(x,y)

def defa(x , y=2):
	print(x,y)	

defa(1)
defa(1,3)

def changePara(*num):
	for s in num :
		print(s)
changePara('s1','s2')
tempVa = ['s1','s2']
changePara(*tempVa)

####带参数名字的可变参数
def chaneKey(name,age,**other):
	print (name , age,"other" , other)
chaneKey('name' , 18 , city='shanghai')	
tempVa={'ke1' : 'v1','ke2':'v2'}
chaneKey('name',17,**tempVa)

def changeKey2(name, age, * ,city='beijing',phone) :
	print(name,age , city,phone)
changeKey2('n1',16,phone='1550157')

#######切片
LIST = ['L1','L2','L3','L4','L5']
print(LIST[0:2])
print(LIST[:3])
print(LIST[-2:])
print(LIST[-2:-1])

LIST = list(range(20))
print(LIST[:10:2])
print(LIST[::3])

#######迭代
dic = {'ke1':'value1' , 'ke2' : 'value2'}
for key in dic :
	print(dic[key])
for value in dic.values():
	print(value)	
for char in 'ABC' :
	print(char)	

from collections import Iterable
print(isinstance('ABC',Iterable))	
print(isinstance(123,Iterable))

for key ,value in enumerate(['v1','v2','v3']) :
	print(key , value)
for x, y in [(1,2) , (3,4)] :
	print(x, y )	

#######列表生成式
L = [x*x for x in range(1,8)]
print(L)
L = [x*x for x in range(1,8) if x % 2 == 0]
print(L)
L = [ x + y for x in 'ABC' for y in '123']
print(L)

L = {'Ke1' : 'Va1','k2' : 'v2'}
for x,y in L.items():
	print(x , y)
L = [x.lower() + '=' + y for x,y in L.items()]
print(L)	

########生成器
print('########生成器')
g = (x * x for x in range (1 , 8))
print(next(g))
print(g)
for x in g:
	print(x)

def myg(max=6) :
	n = 0;
	while (n < max) :
		yield n
		n = n + 1
g = myg(10)	
for x in g :
	print (x)	

def triganel(num=3) :
	L = [0,1 , 2,1]
	L = [ L[i] + L[i + 1]  for i  in range(len(L)-1) ]
	print(L)
triganel(1)	


