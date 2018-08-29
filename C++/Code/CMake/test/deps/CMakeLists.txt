cmake_minimum_required(VERSION 2.6)
project("test")

set (CMAKE_CXX_FLAGS "-g -O3 -Wno-sign-compare")
include_directories(${CMAKE_SOURCE_DIR})

file(GLOB SOURCE_FILES ${CMAKE_SOURCE_DIR}/*.cpp)

add_library(to SHARED ${SOURCE_FILES})

add_executable(t t.cpp)
