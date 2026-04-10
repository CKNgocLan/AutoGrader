@echo off
echo ==================================================
echo    Java Lab Autograder - Build & Launch Script
echo ==================================================
echo.

:: Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java is not installed or not in PATH.
    echo Please install JDK and add it to your system PATH.
    pause
    exit /b
)

echo [1/3] Compiling all Java files...
javac GraderUI.java LecturerGrader.java TestCase.java NumericTests.java

if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Compilation failed! Please check the errors above.
    pause
    exit /b
)

echo [OK] Compilation successful.

echo.
echo [2/3] Creating necessary folders...
if not exist submissions mkdir submissions
if not exist reports mkdir reports

echo [OK] Folders ready.
echo.

echo ==================================================
echo Choose what to run:
echo.
echo   1. GraderUI (Graphical Interface - Recommended)
echo   2. LecturerGrader (Command-line Batch Mode)
echo   3. Exit
echo.

set /p choice="Enter your choice (1-3): "

if "%choice%"=="1" (
    echo.
    echo Launching GraderUI (Graphical Interface)...
    echo Put your student .java files in the "submissions" folder.
    echo.
    java GraderUI
) else if "%choice%"=="2" (
    echo.
    echo Launching LecturerGrader (Command-line Batch)...
    java LecturerGrader
) else if "%choice%"=="3" (
    echo Exiting...
) else (
    echo Invalid choice. Exiting...
)

echo.
echo Script finished.
pause