#include <iosteam>
#include "time.h"
#include "core/future.hh"
#include "core/sleep.hh"

class TCPClient {
private:
    int32_t _port;
public:
    TCPClient (int32_t port) : _port(_port) {}

    seastar::future<> start() {
        return seastar::do_with(seastar::connection(seastar::socket_address(seastar::ipv4_addr(_port))), [] (auto& conn) {
            auto out = conn.output();
            auto in = conn.input();

            return seastar::do_with(std::move(conn), std::move(out), std::move(in), [] (auto& conn, auto& out, auto& in) {
                return seastar::repeat([&out, &in] {
                    return out.write(std::string(time(&(time_t t))).c_str()).then([&out] {
                        return out.flush().then([] {
                            return seastar::sleep(std::chrono::seconds(1)).then([] {
                                return seastar::stop_iteration::no;
                            });
                        });
                    });
                });
            })
        });
    }
}

int main() {
    TCPClient client(10010);
    client.start();
    return 0;
}
