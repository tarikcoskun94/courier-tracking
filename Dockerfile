FROM openjdk:21-jdk-slim
RUN apt-get update && apt-get install -y maven

COPY . /app
WORKDIR /app

RUN mvn clean package -DskipTests
ENTRYPOINT ["java", "-jar", "/app/target/couriertracking-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
