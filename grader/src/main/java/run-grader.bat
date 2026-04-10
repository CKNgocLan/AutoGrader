@echo off
title Java Autograder - Compile

echo ==================================================
echo     Java Lab Autograder - Compile Script
echo ==================================================
echo.

echo Compiling all Java files...

javac Consts.java PathUtils.java GraderUI.java LecturerGrader.java TestCase.java NumericTests.java 
java GraderUI

if %errorlevel% equ 0 (
    echo.
    echo [SUCCESS] Compilation completed successfully!
    echo.
    echo You can now run:
    echo   java GraderUI          (for graphical interface)
    echo   java LecturerGrader    (for command-line batch grading)
)

echo.
pause