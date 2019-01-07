#Set-ExecutionPolicy -Scope CurrentUser RemoteSigned
#!/usr/bin/pwsh

pwsh
javac -d . *.java
jar -cvfm frame.jar manifest.txt com/sqli/framework/*.class 
java -jar frame.jar -hq
pause
