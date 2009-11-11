@rem Make sure that CATALINA_HOME is correctly set
@rem Make sure that you set the name of the war file
@set WAR_NAME=ps-springws

@echo off
:CHECK_ENV
if "%CATALINA_HOME%" == "" GOTO NO_TOMCAT

set TOMCAT_HOME=%CATALINA_HOME%
set CURRENT_DIR=%cd%
set WAR_FILE=%WAR_NAME%.war

:STOP_TOMCAT
echo ---------------------------------------------------------------------------------------------------------------- Stopping Tomcat
cd /d %TOMCAT_HOME%\bin
call shutdown.bat

:CLEAN_BUILD
echo ---------------------------------------------------------------------------------------------------------------- Clean and Build
cd /d %CURRENT_DIR%
call mvn clean install

:DELETE
echo ---------------------------------------------------------------------------------------------------------------- Cleaning up Tomcat
cd /d %TOMCAT_HOME%\work
rmdir /S /Q localhost
cd /d %TOMCAT_HOME%\webapps
del %WAR_FILE%
rmdir /S /Q %WAR_NAME%
cd /d %CURRENT_DIR%

:COPY_WAR
echo ---------------------------------------------------------------------------------------------------------------- Copying the WAR file to Tomcat
cd /d %CURRENT_DIR%
copy target\%WAR_FILE% %TOMCAT_HOME%\webapps

:START_TOMCAT
echo ---------------------------------------------------------------------------------------------------------------- Starting up Tomcat
cd /d %TOMCAT_HOME%\bin
call startup.bat
cd /d %CURRENT_DIR%
goto EXIT

:NO_TOMCAT
echo ---------------------------------------------------------------------------------------------------------------- Error
echo The CATALINA_HOME variable was not found in your system.
echo Please install Tomcat and configure the env correctly.
echo ---------------------------------------------------------------------------------------------------------------- Error
goto EXIT

:EXIT
cd /d %CURRENT_DIR%