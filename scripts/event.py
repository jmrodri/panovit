#!/usr/bin/python

import sys
import fileinput

f = open('/tmp/messages', 'a')
print sys.argv[1]
f.write(sys.argv[1])
f.write('\n')
data = sys.stdin.readlines()
for line in data:
    f.write(line)
f.close()
