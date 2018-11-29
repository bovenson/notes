#/bin/python3

"""
install kazoo:
    sudo pip3 install kazoo
"""

from kazoo.client import KazooClient
import time


# # create client
# zk = KazooClient(hosts='tjwqstaging.zk.hadoop.srv:2181')
zk_server = 'zk1.staging.srv:2181,zk2.staging.srv:2181,zk3.staging.srv:2181,zk4.staging.srv:2181,zk5.staging.srv:2181'
zk = KazooClient(hosts=zk_server)
zk.start()
# # print(zk.exists('/data'))
#
# @zk.DataWatch('/data/services/recommend/config/model')
@zk.DataWatch('com.xiaomi.data.recommend.common.CtrScoreLRService4C')
def mw(data, stat, event):
    print(data)
    print(stat.version)
    print(event)

# # children = zk.get_children('/data/services/recommend/test', watch=mw)
#
while True:
    time.sleep(10)
    print('ok')
