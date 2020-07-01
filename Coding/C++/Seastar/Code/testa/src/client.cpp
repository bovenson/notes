#include <iostream>
#include "core/future.hh"
#include "core/sleep.hh"
#include "net/api.hh"

using namespace std;

using namespace seastar;

class TCPClient {
private:
    int32_t _port;
public:
    TCPClient (int32_t port) : _port(port) {}

    // seastar::future<> send() {

    // }

    seastar::future<> start() {
        return seastar::do_with(seastar::socket_address(seastar::ipv4_addr(_port)), [] (auto& sa) {
            cout << &sa << endl;

            // return seastar::connect(sa).then([] (auto& t) {
            //     cout << &t << endl;
            //     return seastar::make_ready_future<>();
            // });
            // return seastar::do_with(seastar::connect(sa), ([] (auto& conn) {
            //     // cout << &conn << endl;
            //     // auto out = conn.output();
            //     // auto in = conn.input();

            //     return seastar::make_ready_future<>();
            // }));

            // return seastar::do_with(std::move(conn), std::move(out), std::move(in), [] (auto& conn, auto& out, auto& in) {
            //     return seastar::repeat([&out, &in] {
            //         return out.write("hello world").then([&out] {
            //             return out.flush().then([] {
            //                 return seastar::sleep(std::chrono::seconds(1)).then([] {
            //                     return seastar::stop_iteration::no;
            //                 });
            //             });
            //         });
            //     });
            // })
        });
    }
};

int main() {
    TCPClient client(10010);
    client.start();
    return 0;
}
