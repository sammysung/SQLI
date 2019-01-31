#!/bin/bash

cd "$(dirname "$0")"
cd ..
cd ..
javac -d . *.java
jar -cvfm frame.jar manifest.txt com/sqli/framework/*.class 
chmod u+rwx frame.jar
java -jar frame.jar -hq
