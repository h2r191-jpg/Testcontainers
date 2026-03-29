FROM eclipse-temurin:21-jre
EXPOSE 8081
COPY build/libs/Testcontainers-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
