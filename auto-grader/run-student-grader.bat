@echo off
setlocal
:: Parse the third token from the line containing "version"
for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    set JAVAVER=%%g
)

:: Remove quotes from the string (e.g., "1.8.0_291" becomes 1.8.0_291)
set JAVAVER=%JAVAVER:"=%

echo Detected Java Version: %JAVAVER%

echo Running Grader named StudentGraderUI...
echo Make sure you have installed JDK version 19 or higher and downloaded StudentGraderUI.jar,
echo then placed it in the same directory as this batch file.

java -cp StudentGrader.jar student/StudentGrader
exit