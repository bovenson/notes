#include "core/seastar.hh"
#include "core/reactor.hh"
#include "core/future-util.hh"

class TCPServer {
private:
    int32_t _port;
public:
    TCPServer (int32_t port) : _port(port) { }

    seastar::future<> process(seastar::connected_socket s, seastar::socket_address a) {
        auto out = s.output();
        auto in = s.input();

        return seastar::do_with(std::move(s), std::move(out), std::move(in), [] (auto& s, auto& out, auto& in) {
            return seastar::repeat([&out, &in] {
                return in.read().then([&out] (auto buf) {
                    if (buf) {
                        return out.write(std::move(buf)).then([&out] {
                            return out.flush();
                        }).then([] {
                            return seastar::stop_iteration::no;
                        });
                    } else {
                        return seastar::stop_iteration::yes;
                    }
                })
            });
        });
    }

    seastar::future<> start() {
        seastar::listener_options lo;
        lo.reuse_address = true;
        return seastar::do_with(seastar::listen(seastar::make_ipv4_address(_port), lo), [] (auto& listener) {
            return seastar::keep_doing([&listener] () {
                return listener.accept().then(
                    [] (seastar::connected_socket s, seastar::socket_address a) {
                        process(std::move(s), std::move(a));
                    }
                );
            });
        });
    }
}

int main () {
    TCPServer server(10010);
    server.start();
}
