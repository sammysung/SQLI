::dir /s /B *.java > sources.txt
::javac -d . @sources.txt

cd ..
javac -d . *.java
jar -cvfm frame.jar manifest.txt com/sqli/framework/*.class
java -jar frame.jar -hq
pause
