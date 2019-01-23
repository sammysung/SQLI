#!/bin/bash

cd ..
cd ..
mkdir unix
cmake . -G "Unix Makefiles" -B./unix
cd unix
make
