FROM eclipse-temurin:21
WORKDIR /app
COPY target/mavenkube-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]