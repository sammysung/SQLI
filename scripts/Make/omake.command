#!/bin/bash

cd ..
cd ..
mkdir osx
cp scripts/osx/brew.command osx/brew.command
cmake . -G "Unix Makefiles" -B./unix
cd osx
make
make install
