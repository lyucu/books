#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'asyncio'

__author__ = 'Markus Liu'

print('asyncio  demo')
import asyncio 
@asyncio.coroutine
def hello(i):
    print("Hello world!", i)
    # 异步调用asyncio.sleep(1):
    r = yield from asyncio.sleep(1)
    print("Hello again!")

# 获取EventLoop:
loop = asyncio.get_event_loop()
# 执行coroutine
loop.run_until_complete(hello(1))
print('h123')


loop = asyncio.get_event_loop()
tasks = [hello(1), hello(2)]
loop.run_until_complete(asyncio.wait(tasks))
loop.close()