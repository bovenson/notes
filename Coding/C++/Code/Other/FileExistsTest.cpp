#include <iostream>
#include "sys/stat.h"

inline bool fileExistsTest (const std::string& name) {
  struct stat buffer;   
  return (stat (name.c_str(), &buffer) == 0); 
}

int main (int argc, char *argv[]) {
    if (argc > 1) {
        std::cout << fileExistsTest(argv[1]) << std::endl;
    } else {
        std::cout << "Usage: " << argv[0] << " FilePath" << std::endl;
    }
    return 0;
}
