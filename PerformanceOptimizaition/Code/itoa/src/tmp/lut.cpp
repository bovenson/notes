#include <stdint.h>
#include "digitslut.h"
#include "test.h"

// Use lookup table of two digits

void u32toa_lut(uint32_t value, char* buffer) {
    char temp[10];
    char* p = temp;
    
    while (value >= 100) {
        const unsigned i = (value % 100) << 1;
        value /= 100;
        *p++ = gDigitsLut[i + 1];
        *p++ = gDigitsLut[i];
    }

    if (value < 10)
        *p++ = char(value) + '0';
    else {
        const unsigned i = value << 1;
        *p++ = gDigitsLut[i + 1];
        *p++ = gDigitsLut[i];
    }

    do {
        *buffer++ = *--p;
    } while (p != temp);

    *buffer = '\0';
}

void i32toa_lut(int32_t value, char* buffer) {
    uint32_t u = static_cast<uint32_t>(value);
    if (value < 0) {
        *buffer++ = '-';
        u = ~u + 1;
    }
    u32toa_lut(u, buffer);
}

void u64toa_lut(uint64_t value, char* buffer) {
    char temp[20];
    char* p = temp;
    
    while (value >= 100) {
        const unsigned i = static_cast<unsigned>(value % 100) << 1;
        value /= 100;
        *p++ = gDigitsLut[i + 1];
        *p++ = gDigitsLut[i];
    }

    if (value < 10)
        *p++ = char(value) + '0';
    else {
        const unsigned i = static_cast<unsigned>(value) << 1;
        *p++ = gDigitsLut[i + 1];
        *p++ = gDigitsLut[i];
    }

    do {
        *buffer++ = *--p;
    } while (p != temp);

    *buffer = '\0';
}

void i64toa_lut(int64_t value, char* buffer) {
    uint64_t u = static_cast<uint64_t>(value);
    if (value < 0) {
        *buffer++ = '-';
        u = ~u + 1;
    }
    u64toa_lut(u, buffer);
}

REGISTER_TEST(lut);
