#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'udp'

__author__ = 'Markus Liu'

print('udp server demo')
import socket
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(('localhost' , 9999))
print('start udp')
while True :
	data , addr = s.recvfrom(1024)
	print('receive from %s and %s' %(addr , data.decode('utf-8')))
	s.sendto(b'hello %s' % data , addr)
