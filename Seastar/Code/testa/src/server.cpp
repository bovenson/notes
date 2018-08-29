#include "core/seastar.hh"
#include "core/reactor.hh"
#include "core/future-util.hh"
#include "net/socket_defs.hh"
#include "net/ip.hh"
#include "net/socket_defs.hh"
#include <iostream>

using namespace std;
using namespace seastar;

class TCPServer {
private:
    int32_t _port;
public:
    TCPServer (int32_t port) : _port(port) { }

    seastar::future<> process(seastar::connected_socket s, seastar::socket_address a) {
        return
    }

    seastar::future<> start() {
    }
};

int main () {
    TCPServer server(10010);
    server.start();
}
