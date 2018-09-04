#include <iostream>
#include "sstream"
#include <sys/timeb.h>
#include <limits>
#include "sse2.cpp"
#include <stdlib.h>
#include <cstring>

using namespace std;

long long getSystemTime() {
  timeb t;
  ftime(&t);
  return 1000 * t.time + t.millitm;
}

std::string toStringA(long long num) {
  std::ostringstream ss;
  ss << num;

  return ss.str();
}

//struct FMT_API BasicData {
//  static const uint32_t POWERS_OF_10_32[];
//  static const uint64_t POWERS_OF_10_64[];
//  static const char DIGITS[];
//};
//typedef BasicData<> Data;
//
//template <typename T>
//struct MakeUnsigned { typedef T Type; };
//
//template <typename Int>
//inline typename MakeUnsigned<Int>::Type to_unsigned(Int value) {
//  FMT_ASSERT(value >= 0, "negative value");
//  return static_cast<typename MakeUnsigned<Int>::Type>(value);
//}
//
//class FormatInt {
//  private:
//   // Buffer should be large enough to hold all digits (digits10 + 1),
//   // a sign and a null character.
//   enum {BUFFER_SIZE = std::numeric_limits<unsigned long long>::digits10 + 3};
//   mutable char buffer_[BUFFER_SIZE];
//   char *str_;
//
//   // Formats value in reverse and returns the number of digits.
//   char *format_decimal(unsigned long long value) {
//     char *buffer_end = buffer_ + BUFFER_SIZE - 1;
//     while (value >= 100) {
//       // Integer division is slow so do it for a group of two digits instead
//       // of for every digit. The idea comes from the talk by Alexandrescu
//       // "Three Optimization Tips for C++". See speed-test for a comparison.
//       unsigned index = static_cast<unsigned>((value % 100) * 2);
//       value /= 100;
//       *--buffer_end = Data::DIGITS[index + 1];
//       *--buffer_end = internal::Data::DIGITS[index];
//     }
//     if (value < 10) {
//       *--buffer_end = static_cast<char>('0' + value);
//       return buffer_end;
//     }
//     unsigned index = static_cast<unsigned>(value * 2);
//     *--buffer_end = internal::Data::DIGITS[index + 1];
//     *--buffer_end = internal::Data::DIGITS[index];
//     return buffer_end;
//   }
//
//   void FormatSigned(long long value) {
//     unsigned long long abs_value = static_cast<unsigned long long>(value);
//     bool negative = value < 0;
//     if (negative)
//       abs_value = 0 - abs_value;
//     str_ = format_decimal(abs_value);
//     if (negative)
//       *--str_ = '-';
//   }
//
//  public:
//   explicit FormatInt(int value) { FormatSigned(value); }
//   explicit FormatInt(long value) { FormatSigned(value); }
//   explicit FormatInt(long long value) { FormatSigned(value); }
//   explicit FormatInt(unsigned value) : str_(format_decimal(value)) {}
//   explicit FormatInt(unsigned long value) : str_(format_decimal(value)) {}
//   explicit FormatInt(unsigned long long value) : str_(format_decimal(value)) {}
//
//   std::size_t size() const {
//     return to_unsigned(buffer_ - str_ + BUFFER_SIZE - 1);
//   }
//
//   const char *data() const { return str_; }
//
//   const char *c_str() const {
//     buffer_[BUFFER_SIZE - 1] = '\0';
//     return str_;
//   }
//
//   std::string str() const { return std::string(str_, size()); }
//};

int main() {
  char *buff = new char[100];
  int numCount = 1000000;

  for (int i=0; i < numCount; ++i) {
    // int32
    int32_t rd = rand();
    i32toa_sse2(rd, buff);
    string rds = toStringA(rd);
    if (strcmp(buff, rds.c_str()) != 0) {
      cout << "ERROR: " << buff << " - " << rds << endl;
    }

    // uint32
    uint32_t rdu32 = (uint32_t)rd;
    u32toa_sse2(rdu32, buff);
    rds = toStringA(rdu32);
    if (strcmp(buff, rds.c_str()) != 0) {
      cout << "ERROR: " << buff << " - " << rds << endl;
    }

    // int64
    int64_t rd64 = (int64_t)rd;
    i64toa_sse2(rd64, buff);
    rds = toStringA(rd64);
    if (strcmp(buff, rds.c_str()) != 0) {
      cout << "ERROR: " << buff << " - " << rds << endl;
    }

    // uint64
    uint64_t rdu64 = (uint64_t)rd;
    u64toa_sse2(rdu64, buff);
    rds = toStringA(rdu64);
    if (strcmp(buff, rds.c_str()) != 0) {
      cout << "ERROR: " << buff << " - " << rds << endl;
    }
  }

  long long repeat = 100000000;
  char *buffcc = new char[20];
  long long timeStamp = getSystemTime();
  for (int i=0; i < repeat; ++i) {
    u64toa_sse2(1212121312, buffcc);
  }
  std::cout << "Time 0: " << getSystemTime() - timeStamp << std::endl;

  timeStamp = getSystemTime();
  for (int i=0; i < repeat; ++i) {
    char *buffc = new char[20];
    u64toa_sse2(1212121312, buffc);
    delete buffc;
  }
  std::cout << "Time 1: " << getSystemTime() - timeStamp << std::endl;

  timeStamp = getSystemTime();
  for (int i=0; i < repeat; ++i) {
    toStringA(1212121312);
  }
  std::cout << "Time 2: " << getSystemTime() - timeStamp << std::endl;
  return 0;
}
