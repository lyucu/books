#!/usr/bin/env python3.5
# -*- coding: utf-8 -*-
'date'
'datetime timestamp str'
'datatimeoper UTC'

__author__ = 'Markus Liu'

print('date demo')

from datetime import datetime

now = datetime.now()
print(now)

dt = datetime(2014,10,12,12,30,41,123)
print(dt)

############## timestamp
print('timestamp')
tp = dt.timestamp()
print(tp)
ndt = datetime.fromtimestamp(tp)
print(ndt)
ndt = datetime.utcfromtimestamp(tp)
print(ndt)

############ str
print('###### str')
sdata = datetime.strptime('2015-6-1 18:19:50' , '%Y-%m-%d %H:%M:%S')
print(sdata)
s = sdata.strftime('%a %b %d %H:%M')
print(s)

################### operation
print('date operation')
from datetime import timedelta
now = datetime.now()
print(now)
print(now + timedelta(hours=10))
print(now + timedelta(days=1,hours=10))

################# UTC
print('############# UTC')
from datetime import timezone
ch_utc = timezone(timedelta(hours=7))
now = datetime.now()
print(now)
print(now.replace(tzinfo=ch_utc))
print('change')
utc_dt = datetime.utcnow().replace(tzinfo=timezone.utc)
print(utc_dt)
utc_dt = utc_dt.astimezone(timezone(timedelta(hours=7)))
print(utc_dt)
