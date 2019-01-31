#!/bin/bash

cd ..
cd ..
mkdir linux
cp scripts/linux/install*.sh linux
cmake . -G "Unix Makefiles" -B./linux
cd linux
pwd
make install
make
