$direct = Split-Path -Path $MyInvocation.MyCommand.Definition

#This will just run the installer with admin rights; use if you already installed chocolatey
#Start-Process powershell -Verb runAs "cd $direct; ./winInstall.ps1" 

# This will install everything in one fell swoop.
Start-Process powershell -Verb runAs "Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1')); cd $direct; ./winInstall.ps1"
