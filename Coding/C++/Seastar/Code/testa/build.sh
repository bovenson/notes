#!/bin/bash
g++ -std=c++17 `pkg-config --cflags --libs /home/bovenson/Git/Other/seastar/build/release/seastar.pc` -o client src/client.cpp 
g++ -std=c++17 `pkg-config --cflags --libs /home/bovenson/Git/Other/seastar/build/release/seastar.pc` -o server src/server.cpp 