@echo off
setlocal
:: Parse the third token from the line containing "version"
for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    set JAVAVER=%%g
)

:: Remove quotes from the string (e.g., "1.8.0_291" becomes 1.8.0_291)
set JAVAVER=%JAVAVER:"=%

echo Detected Java Version: %JAVAVER%

:: Optional logic: Check if it's a specific version
echo %JAVAVER% | findstr /l "25" >nul
if %ERRORLEVEL% equ 0 (
    echo Your JDK version is 25. You're good to go.
) else (
    echo Your JDK version is NOT 25. You need to use JDK 25 to run Grader.
    pause
    exit
)

java -cp StudentGraderUI.jar student/StudentGraderUI