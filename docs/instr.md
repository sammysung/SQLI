# SQLI
SQL Injection Framework Project

This project aims to create a framework for various SQL Injection attack tests, as well as implement the tests 
themselves and generate meaningful reports.

The current state of the project has the Positive Tainting test implemented, along with a fairly robust CLI for the project. ANLTR4 is a vitial library for the proper funtionality of the code, as it now handles all file parsing and detects vulnerabilities using the listener classes provided.

## Building

Maven scripts are now necessary to compile the entire project (framework code and antlr libraries) together into one executable; as such, any initialization or jar scripts are not usable at this time.

Alternatively, running `mvn clean compile package exec:java` will perform a clean install and run an initial test run of the application; use this to ensure that the jar at least builds properly.

## Usage

Structure:

`java -jar frame.jar [-{h,f,o,d,i,v,q,w}] [input file] [output root filename] [safe file]`

**-h**: Print this help message.

**-f**: Enable command line input file naming mode (first argument will be input filename).

**-o**: Enable command line output file naming mode (second argument will be output filename if -f is present, first otherwise).

**-d**: Enables command line safe query list definition (third argument will be safe filename if other arguments are present).

**-i**: Enable user input output file naming mode (program will prompt for output filename).

**-v**: Enable verbose mode.

**-w**: If an output filename is reused, overwrite without asking.

**-q**: Use this to signal immediate shutdown (focused for testing purposes or help message only runs).

Example: "`java -jar frame.jar -hfov in.txt out.txt`" will print the help message, read from **in.txt**, output to **out.txt**, show a test selection menu, prompt for a safe file, and be verbose.

"`java -jar frame.jar -fdow query.txt test safe.txt`" will pull input data from **query.txt**, a safe file list from **safe.txt**, output to **test{-type}.txt**, and will overwrite **test{-type}.txt** if it already exists with no prompts.

Note that order of the **-f**, **-d**, and **-o** options does not matter; the input name, then the root filename, and then the safe query list file will be read in that order every time.

By default, with no arguments, the program will prompt for user input to assign the input filename and auto generate an output filename with the scheme "*report-{UTC Local Time}.txt*".

Arguments **-i** and **-o** are mutually exclusive. The program will use the autogenerate scheme if they are used together.
