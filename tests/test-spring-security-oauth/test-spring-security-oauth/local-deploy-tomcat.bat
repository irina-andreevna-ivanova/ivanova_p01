rem DO NOT FORGET TO CHANGE THIS OR SPECIFY IT!!!
@SET WAR_FILE_NO_EXT=soauth

rem ________________________________________________________________________________________________________________________________________________________________________________ 
@echo off
cls

SET CURRENT_DIR=%cd%
SET CATALINA_BIN=%CATALINA_HOME%\bin
SET CATALINA_WORK=%CATALINA_HOME%\work
SET CATALINA_WEBAPPS=%CATALINA_HOME%\webapps
SET WAR_FILE=%WAR_FILE_NO_EXT%.war

rem echo ________________________________________________________________________________________ Stopping Tomcat
rem cd /D %CATALINA_BIN%
rem call %CATALINA_BIN%\shutdown.bat

echo ________________________________________________________________________________________ Cleaning and packaging the WAR file
cd /D %CURRENT_DIR%
call mvn clean package

echo ________________________________________________________________________________________ Cleaning Tomcat /webapps and /work
rmdir /S /Q %CATALINA_WORK%\Catalina\localhost
rmdir /S /Q %CATALINA_WEBAPPS%\%WAR_FILE_NO_EXT%
del %CATALINA_WEBAPPS%\%WAR_FILE%

echo ________________________________________________________________________________________ Deploying WAR file
cd /D %CURRENT_DIR%
copy target\%WAR_FILE% %CATALINA_WEBAPPS%

echo ________________________________________________________________________________________ Starting Tomcat
call %CATALINA_HOME%\bin\startup.bat
