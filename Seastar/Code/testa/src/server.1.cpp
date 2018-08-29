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

    static seastar::future<> process(seastar::connected_socket s, seastar::socket_address a) {
        return seastar::make_ready_future<>();
        // auto out = s.output();
        // auto in = s.input();

        // return seastar::do_with(std::move(s), std::move(out), std::move(in), [] (auto& s, auto& out, auto& in) {
        //     return seastar::repeat([&out, &in] {
        //         return in.read().then([&out] (auto buf) {
        //             if (buf) {
        //                 return out.write(std::move(buf)).then([&out] {
        //                     return out.flush();
        //                 }).then([] {
        //                     return seastar::stop_iteration::no;
        //                 });
        //             } else {
        //                 return seastar::stop_iteration::yes;
        //             }
        //         })
        //     });
        // });
    }

    seastar::future<> start() {
        cout << _port << endl;
        seastar::listen_options lo(true);
        seastar::socket_address sa(seastar::ipv4_addr(0, _port));
        return seastar::do_with(seastar::listen(sa, lo), [] (auto& listener) {
            return seastar::keep_doing([&listener] () {
                return listener.accept().then(
                    [] (seastar::connected_socket s, seastar::socket_address a) {
                        // process(std::move(s), std::move(a));
                        auto out = s.output();
                        return seastar::make_ready_future<>();
                    }
                );
            });
        });
    }
};

int main () {
    TCPServer server(10010);
    server.start();
}
