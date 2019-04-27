#!/usr/bin/env bash

cd ..
cd ..
mvn clean compile package
cp target/frame.jar .
