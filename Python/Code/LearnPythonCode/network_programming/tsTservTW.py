# coding: utf-8

from twisted.internet import protocol, reactor
from time import ctime

PORT = 21567

class TSServProtocol(protocol.Protocol):
    def connectionMade(self):   # 客户端连接到服务器时就会执行
        clnt = self.clnt = self.transport.getPeer().host
        print('...connected from:', clnt)

    def dataReceived(self, data):   # 当服务器收到客户端通过网络发送的一些数据时就会调用 dataReceived() 方法
        self.transport.write(bytes('[%s] %s' % (ctime(), data), 'utf-8'))

factory = protocol.Factory()
factory.protocol = TSServProtocol
print('waiting for connection...')
reactor.listenTCP(PORT, factory)
reactor.run()