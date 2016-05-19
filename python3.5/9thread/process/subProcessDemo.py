#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'subprocess'

__author__ = 'Markus Liu'

############# sub process
import subprocess
print('start subprocess')
r = subprocess.call(['nslookup','www.baidu.com'])
print('end') 

print('start again')
p = subprocess.Popen(['nslookup'] ,stdin=subprocess.PIPE,stdout=subprocess.PIPE,stderr=subprocess.PIPE)
out , err = p.communicate(b'set q=mx\npython.org\nexit\n')
print(out.decode('UTF-8'))
print('Exit ' , p.returncode)
