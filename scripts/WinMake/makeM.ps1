cd ..
cd ..
cmake . -S . -G "MinGW Makefiles" -B ./mingw
cp scripts/pwsh/help*.ps1 mingw
cd mingw
mingw32-make.exe install
mingw32-make.exe
cd ..
cd scripts/WinMake