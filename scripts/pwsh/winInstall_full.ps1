# Read this before running! If it'll even let you right off..
# This will install notepad++, jdk11, ruby, and the community edition of intellj; this happens after chocolatey itself is installed.
# The way chocolatey works, it seems it will reinstall them if you did this through other means, but it will also setup the path for you correctly.
# Must be ran as Admin to work properly!
# This really should only need to be ran once, but let me know if something goes wrong.

choco install notepadplusplus -y
choco install jdk11 -y
choco install ruby -y
choco install intellijidea-community -y
choco install eclipse -y
choco install cmake --installargs 'ADD_CMAKE_TO_PATH=System' -y
choco install visualstudio2017community --package-parameters "--add Microsoft.VisualStudio.Workload.NativeCrossPlat;includeRecommended --add Microsoft.VisualStudio.Workload.NativeDesktop;includeRecommended --passive --locale en-US" -y
choco install mingw -y
choco install vim -y
choco install msys2 -y
choco install vscode -y
refreshenv