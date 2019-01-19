:: Only really useful after running the chocolatey installer in Powershell

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
refreshenv