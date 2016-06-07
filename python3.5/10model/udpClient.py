#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'udp'

__author__ = 'Markus Liu'

print('udp client demo')
import socket
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
for data in [b'123',b'b' , b'c'] :
	s.sendto(data , ('localhost', 9999))
	print(s.recv(1024).decode('utf-8'))
s.close()	