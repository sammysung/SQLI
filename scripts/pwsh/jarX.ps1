# Set-ExecutionPolicy -Scope CurrentUser RemoteSigned

# This version should be used on Windows and Linux, and possibly OSX 10.12 and up.

# javac -d . *.java
# jar -cvfm frame.jar manifest.txt com/sqli/framework/*.class

# This version should be used on Mac, or at least OSX 10.11 and lower.

cd ..
cd ..
javac -d . frame.java build.java taint.java list.java node.java backend.java
jar -cvfm frame.jar manifest.txt com/sqli/framework/frame.class com/sqli/framework/build.class com/sqli/framework/backend.class com/sqli/framework/node.class com/sqli/framework/taint.class com/sqli/framework/list.class

java -jar frame.jar -hq
pause