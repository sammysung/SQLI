cmake_minimum_required (VERSION 2.6)

# The options here are:

# lite:  For setting up just CMake and Java (provided to maintain compatibility with the project). Will also include mingw for Windows, for CLI use with CMake.
#        Should be very quick, and download very little, but some scripts will fail without the proper programs installed (included anything Ruby related).

# stand: For the recommened, default installation. Will include the lite package, as well as Powershell, Visual Studio Code (except for Windows, where Visual Studio
#        will be used instead), and the Ruby packages to run a local copy of the website. This will take a bit of time and a fair amount of data usage.

# full:  This will install all optional packages for all OS' as well. Do note that this will take quite some time, especially on Windows due to Visual Studio.
#        Use if you want to test exporting to various IDEs.

set(Type "stand")

find_package(Java REQUIRED)
include(UseJava)

enable_testing()

project (SQLI)

set(CMAKE_JAVA_COMPILE_FLAGS)

install(CODE "MESSAGE(\"You have selected the '${Type}' install option.\")")
install(CODE "MESSAGE(\"\")")

if (APPLE)
   install(CODE "MESSAGE(\"Running OSX install script.\")")
   install(CODE "MESSAGE(\"\")")
   install(CODE "execute_process(COMMAND bash ./brew_${Type}.command)")
endif (APPLE)

if (WIN32)
   install(CODE "MESSAGE(\"Running Windows install script.\")")
   install(CODE "MESSAGE(\"\")")
   install(CODE "execute_process(COMMAND powershell.exe ./help_${Type}.ps1)")
endif ()

if (UNIX AND NOT APPLE)
   install(CODE "MESSAGE(\"Running Linux (Debian) install script.\")")
   install(CODE "MESSAGE(\"\")")
   install(CODE "execute_process(COMMAND bash ./install_${Type}.sh)")
endif ()

add_jar(frame frame.java build.java backend.java node.java list.java taint.java MANIFEST manifest.txt)

get_target_property(_jarFile frame JAR_FILE)
get_target_property(_classDir frame CLASSDIR)
get_target_property(in frame INSTALL_FILES)

message(STATUS "Jar file ${_jarFile} created!")
message(STATUS "Class files were created in ${_classDir}")
message(STATUS "These files should be used: ${in}")
