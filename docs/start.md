---
layout: single
title: Getting Started
permalink: /start/
---
# Overall Notes

Many of the things mentioned here can and have been automated via the use of scripts; if it would prove useful, these scripts can be supplied on this site on request.

Java is the main dependency for this project at this state, though using Powershell as it is (surprisingly) cross-platform is a good idea if you would like to go the script route.

The [Powershell Github repository](https://github.com/PowerShell/PowerShell#get-powershell) is a great place to start should you choose to use it (Windows has this by default, too).

With that said, there are Bash shell scripts, as well as Windows cmd batch and Mac command files available as well.

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

**IMPORTANT NOTE!!!**

_The test system for this is a OSX 10.10.5 Yosemite install, and as such, you may or may not have some tools preinstalled if you have a more current system. Likewise, older systems may not be able to use some of the tools; if this is an issue, please let the team know and we may be able to help you._

With that said, installing the newest Java install is pretty simple. Just go grab the [Java 11 JDK .DMG](https://www.oracle.com/technetwork/java/javase/downloads/index.html). Run that, follow what the archive asks, and you’re good to go!

You could also install [Homebrew](https://brew.sh/), then run `brew cask install java` and let it do the work for you. Homebrew is wonderful for development on Mac in general, since options are otherwise limited.

If that doesn’t sound good, there is another option in [MacPorts](https:/www.macports.org/install.php), just be aware that you’ll need Xcode installed to use this properly while Homebrew does not.

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

**Please run the command**

`ruby -v` 

**in a terminal before doing anything else here, you need to know what your Ruby version is!**

If the result of the previous command was 2.2.0 or higher, then you are fine. OSX installs come with built-in versions of Ruby, so if this is the case for you, everything should just work. If your Ruby install is lower, or the scripts fail anyways, then you’ll want to follow the below.

Installing this updated version, at least from my testing, proved to be a bit cumbersome. [This Stack Overflow page](https://stackoverflow.com/questions/38194032/how-to-update-ruby-version-2-0-0-to-the-latest-version-in-mac-osx-yosemite) offers two ways to approach this problem; while the first, accepted solution is what I got working on my system, and is 100% recommended for anyone planning to seriously use Ruby outside of this project, the second answer, titled “**Brew only solution**” should be attempted first.

Simply put, the first solution installs the whole Ruby development kit, which is unnecessary for simply running the server gem included here. [Homebrew](https://brew.sh/) will handle just what is needed for the script, be more lightweight, and is more versatile 
in that it can install other apps.

## Powershell on Mac Notes

If you happen to be using some older install of OSX (specifically, 10.11 or lower), you won’t be able to install the latest version of Powershell due to dependency issues. If you really, really want to run the Powershell scripts, then you’ll need to download an older version. I found that [Alpha 17](https://github.com/PowerShell/PowerShell/releases/tag/v6.0.0-alpha.17) worked for 10.10, and there should be somewhat newer versions that work with 10.11. 

Also, the best way to install Powershell via Microsoft’s own install pages is to use Homebrew using 

`brew cask install powershell`

which will work great on 10.12 or greater.