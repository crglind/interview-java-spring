@echo off
echo Building jar file
CALL gradlew.bat build --no-daemon

set IMAGE_NAME=ri-interview-backend
CALL docker ps --quiet || goto :RunNoDocker

CALL docker image inspect %IMAGE_NAME%') || CALL docker build -t ri-interview-backend .
CALL docker run -p 8080:8080 -v "%cd%\build\libs\spending.jar":"/app/main.jar" --rm %IMAGE_NAME%

:RunNoDocker
echo Docker not found, running locally
CALL gradlew.bat bootRun
