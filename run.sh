#!/bin/sh

IMAGE_NAME=ri-interview-backend

./gradlew build --no-daemon
chmod -R 755 ./build/libs

if ! docker image inspect $IMAGE_NAME > /dev/null 2>&1; then
  echo "Docker image not found, building..."
  docker build -t ri-interview-backend .
fi

docker run -p 8080:8080 -v ./build/libs/spending.jar:/app/main.jar --rm $IMAGE_NAME || ./gradlew bootRun
