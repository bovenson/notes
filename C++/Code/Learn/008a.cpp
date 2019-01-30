#include <iostream>
#include <vector>


    // std::cout << datas[0] << " - " << typeid(datas[0]).name() << endl;
    // std::cout << datas[0]->internal_ << " - " << typeid(datas[0]->internal_).name() << endl;

using namespace std;

class Data {
    public:
    virtual ~Data() { }
};

template <typename T>
class WrapperData : public Data {
    public:
    T internal_;
    template<typename... Args>
    WrapperData(Args&&... args) : internal_(std::forward<Args>(args)...) {}
};

template <typename... Targs>
std::vector<Data*> Args(Targs&&... args) {
    return std::vector<Data*>({
        new WrapperData<typename std::remove_cv<typename std::remove_reference<Targs>::type>::type>(std::forward<Targs>(args))...
    });
}

int main() {
    std::string s = "2";
    std::vector<Data*> datas = Args(0, 1, s, 3.0);
    std::cout << datas.size() << endl;
    std::cout << ((WrapperData<int> *)datas[0])->internal_ << std::endl;
    std::cout << ((WrapperData<int> *)datas[1])->internal_ << std::endl;
    std::cout << ((WrapperData<std::string> *)datas[2])->internal_ << std::endl;
    std::cout << ((WrapperData<double> *)datas[3])->internal_ << std::endl;
    return 0;
}