@echo off
javac TestCase.java NumericTests.java Autograder.java ./submissions/Main.java LecturerGrader.java
java LecturerGrader
pause