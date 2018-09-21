#include <iostream>
#include <memory>
using namespace std;

class HasPtr
{
public:
    HasPtr(char *s);
    ~HasPtr();

private:
    char *ptr;
};

HasPtr::HasPtr(char *s)
{
    if (s == nullptr)
    {
        ptr = new char[1];
        *ptr = '\0';
    }
    else
    {
        ptr = new char[strlen(s) +1];
        strcpy(ptr, s);
    }
}

HasPtr::~HasPtr()
{
    cout << "destructor ---> " << ptr << endl;
    delete ptr;
}

int main()
{
    auto_ptr<HasPtr>p1(new HasPtr("Book"));
    auto_ptr<HasPtr>p2(p1); // 所有权转移到p2,p1变为empty

    return 0;
}
