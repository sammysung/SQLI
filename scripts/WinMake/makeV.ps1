cd ..
cd ..
cmake . -S . -G "Visual Studio 15 2017" -B ./visual
cp scripts/pwsh/help*.ps1 visual
cd visual
start SQLI.sln
cd ..
cd scripts/WinMake