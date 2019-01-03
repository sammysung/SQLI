<<<<<<< Updated upstream
# Set-ExecutionPolicy -Scope CurrentUser RemoteSigned
# This version should be used on Windows and Linux, and possibly OSX 10.12 and up.

=======
#Set-ExecutionPolicy -Scope CurrentUser RemoteSigned
#!/usr/bin/pwsh
>>>>>>> Stashed changes
javac -d . *.java
jar -cvfm frame.jar manifest.txt com/sqli/framework/*.class
java -jar frame.jar -hq
pause
