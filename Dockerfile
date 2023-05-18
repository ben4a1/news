FROM openjdk:17-jdk-alpine

COPY news-service/build/libs/news-service.jar news-app.jar

ENTRYPOINT [ "java", "-jar", "news-app.jar" ]