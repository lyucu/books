#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'collection'
'namedtuple dequeue defaultdict'
'ordereddict counter'
__author__ = 'Markus Liu'

print('collection demo')

############# namedtuple

from collections import namedtuple
PP = namedtuple('PPP' , ['x','y'])
p = PP(1,2)
print(p.x)

########### dequeue
print('#### dequeue')

from collections import deque
q = deque(['a','b','c'])
q.append('x')
q.appendleft('y')
print(q)
q.pop()
q.popleft()
print(q)

############# defaultdict
print('###### defaultdict')
from collections import defaultdict
dd = defaultdict(lambda : 'N/A')
dd['k1'] = 'v1'
print(dd['ke'])
print(dd['k1'])

################## ordereddict
print('###### ordered dict')
from collections import OrderedDict
od = OrderedDict([('a','v') , ('b' , 'w'),('c' , 'z')])
print(od)

################ counter
print('######### counter')
from collections import Counter
c = Counter('Progrem')
print(c)
