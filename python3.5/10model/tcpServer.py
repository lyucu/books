#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'tcp'

__author__ = 'Markus Liu'

print('tcp server demo')
import socket,threading,time

s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind(('localhost' , 9999))

s.listen(5)
print('server start')





def tcpMo(sock , address) :
	print('accept form ' , address)
	sock.send(b'welcome')
	while True :
		data = sock.recv(1024)
		time.sleep(1)
		if not data or data.decode('utf-8')== 'exit' :
			break
		sock.send(('hellow' +data.decode('utf-8')).encode('utf-8'))
	sock.close()
	print('connection close')

while True :
	sock ,address = s.accept()
	t = threading.Thread(target=tcpMo,args=(sock,address))
	t.start()