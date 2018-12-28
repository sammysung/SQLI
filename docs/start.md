---
layout: single
title: Getting Started
permalink: /start/
---
# Overall Notes

Many of the things mentioned here can and have been automated via the use of scripts; if it would prove useful, these scripts can be supplied on this site on request.

Java is the main dependency for this project at this state, though using Powershell as it is (surprisingly) cross-platform is a good idea if you would like to go the script route.

The [Powershell Github repository](https://github.com/PowerShell/PowerShell#get-powershell) is a great place to start should you choose to use it (Windows has this by default, too).

With that said, there are Bash shell scripts, as well as Windows cmd batch files available as well. Mac works like Linux from what I remember, which will be tested soon.

## Java Initial Setup

Java will need to be setup on your system in order to run this program. As it is currently a command line only tool (though this is set to change) you will need to setup a JDK for your system and ensure that command line tools can use it. 

Please note that our development versions of Java are currently between 10 and 11; if you cannot run the jar due to an "Unsupported Class Error", either update or let the team know so we can aim for higher compatibility, if possible. Below are steps to install and/or setup Java, depending on your system OS:

### Linux (Ubuntu-based)

Simply open up a terminal and run the command:

`sudo apt install default-jre`

This should get you the most recent Java installation up and running without too much hassle. 

If for some reason you do not receive Java 11 with this command, you can try this series instead:

```bash
wget https://download.java.net/java/GA/jdk11/28/GPL/openjdk-11+28_linux-x64_bin.tar.gz -O /tmp/openjdk-11+28_linux-x64_bin.tar.gz

sudo tar xfvz /tmp/openjdk-11+28_linux-x64_bin.tar.gz --directory /usr/lib/jvm

rm -f /tmp/openjdk-11+28_linux-x64_bin.tar.gz
```

### Windows

First, install Java 11 from [this site here](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html).

You will want the Windows file that ends in `.exe`. That install will allow you to run Java programs everywhere but the command line by default.

To get command line Java up and running, follow the instructions [located here](https://javatutorial.net/set-java-home-windows-10). If you have trouble with this guide, a few more are provided down below.

This will allow you to run java commands from any command line instance, which is not strictly necessary, but highly useful for any Java development on Windows.

If you are running Windows 10, you could also take advantage of the [ability to run Linux as an application inside Windows](https://docs.microsoft.com/en-us/windows/wsl/install-win10).

How-ToGeek has a good guide on that as well [located here](https://www.howtogeek.com/249966/how-to-install-and-use-the-linux-bash-shell-on-windows-10/).

### Mac

Coming soon! I haven't fixed my internet kexts on my Mac install, and I don't have my wireless dongle stopgap measure for it yet, either.

### Pathing

If, for some reason, you need to run a seperate Java install or the Windows instructions were not clear, this is the offical Oracle documentation about the PATH variable for all systems (including Solaris, for anyone who happens to use that):

<https://java.com/en/download/help/path.xml>

<https://docs.oracle.com/javase/tutorial/essential/environment/paths.html>

## Ruby

Once it is available, if you would like to use the website hosting tools used to make this site, you will need to have Ruby installed and ready for deployment. 

For all platforms, a terminal will need to be opened and the following command ran:

`gem install bundler`

Jekyll, and therefore Github Pages, needs this technology to do anything.

Run this on all platforms to install all needed bundles, check for updates, and begin hosting the page locally:

```Ruby
bundle install
bundle update
bundle exec jekyll serve
```

If the website info is released, these will be built into scripts for ease of use.

### Linux Install

Run this command in order to install Ruby and the dependencies for Pages:

`sudo apt-get install build-essential patch ruby-dev zlib1g-dev liblzma-dev ruby-all-dev`

### Windows Install

Download the [RubyInstaller for Windows](https://rubyinstaller.org/) package, and install it. 

This will fix the PATH for you, but if something goes wrong there, the above Java documentation may be able to help.

Powershell or CMD will be able to run Ruby now.

### Mac Install

Coming Soon!

