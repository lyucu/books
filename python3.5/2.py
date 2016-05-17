#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-

#list
className = ['1','2','3']
print(className)
print(len(className))
print(className[1])
print(className[-1])

className.append('123')
print(className)

className.insert(1,'insert')
print(className)

className.pop()
print(className)

className.pop(0)
print(className)

className[0]='update'
print(className)

#touple
to = (1,2)
print(to)

to = (1)
print(to)

to = (1,)
print(to)

#if else
age = 20
if age >= 6:
    print('teenager')
elif age >= 18:
    print('adult')
else:
    print('kid')

#int()
s = input('birthdat: \n')
if s and int(s) > 2000 :
	print('>2000')
else :
	print('<2000')

#loop
names = ['n1','n2','n3']
for n in names :
	print(n)

for n in ['1','3','2','4']:
	print(n)

print(list(range(5)))

#while
n = 0
while n < 2 :
	print('while')
	n = n + 1

#dict
dic = {'ke1':'va1','ke2':'va2'}
print(dic)	
print(dic['ke1'])
dic['ke1'] = 'change1'
print(dic['ke1'])

print('ke1' in dic)
print(dic.get('ke3' , 'va3'))

dic.pop('ke2')
print(dic)

#set
print('#######set')
my_set = set([1,2,3,4,4])
print(my_set) 
my_set.add(5)
print(my_set)
my_set.remove(4)
print(my_set)
mys1 = set([1,2])
mys2 = set([2,3])
print(mys2 & mys1)
print(mys1 | mys2)
