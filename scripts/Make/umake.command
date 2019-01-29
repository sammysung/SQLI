#!/bin/bash

cd ..
cd ..
mkdir unix
cp scripts/osx/brew.command unix/brew.command
cmake . -G "Unix Makefiles" -B./unix
cd unix
make
make install
