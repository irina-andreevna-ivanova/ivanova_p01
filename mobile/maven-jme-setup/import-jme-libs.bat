@echo off
if "%JME_HOME%" == "" GOTO NO_JME
goto INSTALL

:NO_JME
echo ERROR: the JME_HOME variable was not found on your system.
ehco Please install JavaME (http://java.sun.com/javame/index.jsp) and then try again
goto EXIT

:INSTALL
set JME_LIB=%JME_HOME%\lib
echo Installing cldc-1.1 
call mvn install:install-file -DgroupId=javax.microedition -DartifactId=cldc -Dversion=1.1 -Dpackaging=jar -Dfile=%JME_LIB%\cldc_1.1.jar
echo Installing midp-2.1 
call mvn install:install-file -DgroupId=javax.microedition -DartifactId=midp -Dversion=2.1 -Dpackaging=jar -Dfile=%JME_LIB%\midp_2.1.jar

:EXIT
