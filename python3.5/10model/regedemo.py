#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'regex 正则表达式'
'match split group compile'

__author__ = 'Markus Liu'

print('regex demo')

import re
################## match
s = 'ABC\\-001'
s = r'ABC\-001'

if (re.match(s , 'ABC-002')):
	print('sucess')
else :
	print('faile')

################# split

print('a b   c'.split(' '))		
print(re.split(r'[\s\,\;]+' ,'a,b,;  c' ))

#################  group
print('######## group')
m = re.match(r'^(\d{3})-(\d{3,8})$','010-32123')
print(m.group(0))
print(m.group(1))
print(m.group(2))
print(m.groups())

################# compile
print('########## compile')
re_tel = re.compile(r'^(\d{3})-(\d{3,8})$')
print(re_tel.match('010-32123').groups())
