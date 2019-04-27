$direct = Split-Path -Path $MyInvocation.MyCommand.Definition

cd $direct
cd ..
cd scripts/pwsh
./choco_lite.ps1