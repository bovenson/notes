#include <iostream>

using namespace std;

class RefCounted {
 public:
  explicit RefCounted(int ref = 1) : ref_(ref) {}
  virtual ~RefCounted() {}
  void Ref() {
    ref_++;
  }
  void UnRef() {
    if (--ref_ == 0) {
      delete this;
    }
  }
 private:
  std::atomic<int64_t> ref_;
};

class A : public RefCounted {
    public:
    ~A() {
        std::cout << "DOEN" << std::endl;
    }
};

int main() {
    A *a = new A();
    a->UnRef();
    return 0;
}
