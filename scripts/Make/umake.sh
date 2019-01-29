#!/bin/bash

cd ..
cd ..
mkdir unix
cp scripts/linux/install.sh unix/install.sh
cmake . -G "Unix Makefiles" -B./unix
cd unix
make
make install
