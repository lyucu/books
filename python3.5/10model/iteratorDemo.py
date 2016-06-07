#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'iterator'

__author__ = 'Markus Liu'

print('iterator demo')

import itertools
natural = itertools.count(1)
natural = itertools.takewhile(lambda x: x <= 10, natural)
for n in natural :
	print(n)

cyc = itertools.cycle('ABC')	
cyc = itertools.takewhile(lambda x: x == 'A' , cyc)
for c in cyc :
	print(c)

ns = itertools.repeat('A',3)
for n in ns :
	print(n)	

itertools.chain('aBC' , 'XBY')

itertools.groupby('AAABBBCCC')	