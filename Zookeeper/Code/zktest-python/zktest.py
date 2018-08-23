#/bin/python3

"""
install kazoo:
    sudo pip3 install kazoo
"""

from kazoo.client import KazooClient
import time


# create client
zk = KazooClient(hosts='tjwqstaging.zk.hadoop.srv:2181')
zk.start()
# print(zk.exists('/data'))

@zk.DataWatch('/data/services/recommend/test')
def mw(data, stat, event):
    print(data)
    print(stat.version)
    print(event)

# children = zk.get_children('/data/services/recommend/test', watch=mw)

while True:
    time.sleep(10)
    print('ok')
