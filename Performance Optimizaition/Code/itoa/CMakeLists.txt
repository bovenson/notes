cmake_minimum_required(VERSION 2.6)
project(itoa)

set(CMAKE_CXX_STANDARD 98)
set (CMAKE_CXX_FLAGS "-g -O3 -Wno-sign-compare")

include_directories (${CMAKE_SOURCE_DIR}/include)
file(GLOB SOURCE_FILES ${CMAKE_SOURCE_DIR}/src/*.cpp )


add_executable(itoa src/main.cpp)