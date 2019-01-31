#!/bin/bash

cd ..
cd ..
mkdir osx
cp scripts/osx/brew*.command osx
cmake . -G "Unix Makefiles" -B./osx
cd osx
pwd
make install
make
