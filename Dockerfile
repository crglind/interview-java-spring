FROM eclipse-temurin:17-alpine

RUN mkdir /app

WORKDIR /app

RUN chmod -R 755 /app

EXPOSE 8080

CMD ["java", "-jar", "main.jar"]
