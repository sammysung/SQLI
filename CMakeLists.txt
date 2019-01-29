cmake_minimum_required (VERSION 2.6)

find_package(Java REQUIRED)
include(UseJava)

enable_testing()

project (SQLI)

set(CMAKE_JAVA_COMPILE_FLAGS)

if (APPLE)
   install(CODE "MESSAGE(\"Running OSX install script.\")")
   install(CODE "MESSAGE(\"\")")
   install(CODE "execute_process(COMMAND bash ./brew.command)")
endif (APPLE)

if (WIN32)
   install(CODE "MESSAGE(\"Running Windows install script.\")")
   install(CODE "MESSAGE(\"\")")
   install(CODE "execute_process(COMMAND powershell.exe ./help.ps1)")
endif ()

if (UNIX AND NOT APPLE)
   install(CODE "MESSAGE(\"Running Linux (Debian) install script.\")")
   install(CODE "MESSAGE(\"\")")
   install(CODE "execute_process(COMMAND bash ./install.sh)")
endif ()

add_jar(frame frame.java build.java backend.java node.java list.java taint.java MANIFEST manifest.txt)

get_target_property(_jarFile frame JAR_FILE)
get_target_property(_classDir frame CLASSDIR)
get_target_property(in frame INSTALL_FILES)

message(STATUS "Jar file ${_jarFile} created!")
message(STATUS "Class files were created in ${_classDir}")
message(STATUS "These files should be used: ${in}")
