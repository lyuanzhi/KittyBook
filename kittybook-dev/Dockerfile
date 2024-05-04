FROM maven:3.8.3-openjdk-8 AS builder
RUN USER=root mkdir kittyBack
WORKDIR /kittyBack
COPY . .
RUN mvn clean package
# Final stage
FROM openjdk:8-jdk-slim
COPY --from=builder /kittyBack/demo-controller/target/demo-controller-0.0.1-SNAPSHOT.war ./kitty.war
CMD ["java", "-jar", "kitty.war"]
