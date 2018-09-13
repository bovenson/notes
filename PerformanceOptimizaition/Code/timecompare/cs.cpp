#include <iostream>

class BaseFeatureV1 {
 public:

  static const char* ascii_fingerprint; // = "0446D8BEB7AB38666AF2B2265D91F7D0";
  static const uint8_t binary_fingerprint[16]; // = {0x04,0x46,0xD8,0xBE,0xB7,0xAB,0x38,0x66,0x6A,0xF2,0xB2,0x26,0x5D,0x91,0xF7,0xD0};

  BaseFeatureV1() : id(0), identifier(0), fea(""), value(0), weight(0) {
  }

  virtual ~BaseFeatureV1() throw() {}

  int64_t id;
  int64_t identifier;
  std::string fea;
  double value;
  double weight;
};

class BaseFeatureV2 {
 public:

  static const char* ascii_fingerprint; // = "148F3AAAC1D9859963D5E800D187BF26";
  static const uint8_t binary_fingerprint[16]; // = {0x14,0x8F,0x3A,0xAA,0xC1,0xD9,0x85,0x99,0x63,0xD5,0xE8,0x00,0xD1,0x87,0xBF,0x26};

  BaseFeatureV2() : identifier(0) {
  }

  virtual ~BaseFeatureV2() throw() {}

  int64_t identifier;
};

