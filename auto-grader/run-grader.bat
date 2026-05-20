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

java -cp StudentGraderUI.jar student/StudentGraderUI
pause
exit

:: Optional logic: Check if it's a specific version
@REM echo %JAVAVER% | findstr /l "25" > nul
@REM if %ERRORLEVEL% equ 0 (
@REM     echo Your JDK version is 25. Running Grader named StudentGraderUI_jdk25...
@REM     echo Make sure you have downloaded StudentGraderUI_jdk25.jar and placed it in the same directory as this batch file.
@REM     java -cp StudentGraderUI_jdk25.jar student/StudentGraderUI
@REM     pause
@REM     exit
@REM )
@REM echo %JAVAVER% | findstr /l "19" > nul
@REM if %ERRORLEVEL% equ 0 (
@REM     echo Running Grader named StudentGraderUI_jdk19...
@REM     echo Make sure you have downloaded StudentGraderUI_jdk19.jar and placed it in the same directory as this batch file.
@REM     java -cp StudentGraderUI_jdk19.jar student/StudentGraderUI
@REM     pause
@REM     exit
@REM ) else (
@REM     echo Your JDK version is NOT 19 or above. You need to use JDK version 19 or higher to run Grader.
@REM     pause
@REM     exit
@REM )
@REM echo %JAVAVER% | findstr /l "24" > nul
@REM if %ERRORLEVEL% equ 0 (
@REM     echo Your JDK version is 24. Running Grader named StudentGraderUI_jdk24...
@REM     echo Make sure you have downloaded StudentGraderUI_jdk24.jar and placed it in the same directory as this batch file.
@REM     java -cp StudentGraderUI_jdk24.jar student/StudentGraderUI
@REM     pause
@REM )
@REM echo %JAVAVER% | findstr /l "21" > nul
@REM if %ERRORLEVEL% equ 0 (
@REM     echo Your JDK version is 21. Running Grader named StudentGraderUI_jdk21...
@REM     echo Make sure you have downloaded StudentGraderUI_jdk21.jar and placed it in the same directory as this batch file.
@REM     java -cp StudentGraderUI_jdk21.jar student/StudentGraderUI
@REM     pause
@REM )
@REM echo %JAVAVER% | findstr /l "20" > nul
@REM if %ERRORLEVEL% equ 0 (
@REM     echo Your JDK version is 20. Running Grader named StudentGraderUI_jdk20...
@REM     echo Make sure you have downloaded StudentGraderUI_jdk20.jar and placed it in the same directory as this batch file.
@REM     java -cp StudentGraderUI_jdk20.jar student/StudentGraderUI
@REM     pause
@REM )
@REM echo %JAVAVER% | findstr /l "17" > nul
@REM if %ERRORLEVEL% equ 0 (
@REM     echo Your JDK version is 17. Running Grader named StudentGraderUI_jdk17...
@REM     echo Make sure you have downloaded StudentGraderUI_jdk17.jar and placed it in the same directory as this batch file.
@REM     java -cp StudentGraderUI_jdk17.jar student/StudentGraderUI
@REM     pause
@REM )