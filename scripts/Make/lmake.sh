#!/bin/bash

cd ..
cd ..
mkdir linix
cp scripts/linux/install.sh linix/install.sh
cmake . -G "Unix Makefiles" -B./unix
cd linix
make install
make
