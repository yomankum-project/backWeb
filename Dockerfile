# Stage 1: Build stage using Gradle
FROM openjdk:17-alpine as build

# Set the working directory inside the container
WORKDIR /app

# Copy necessary files for the Gradle build
COPY build/libs/*.jar /app/yomankum.jar

# Stage 2: Create a minimal image to run the application
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the previous stage to this new stage
COPY --from=build /app/yomankum.jar /app/yomankum.jar

# Expose the port your application runs on (change as per your Spring Boot configuration)
EXPOSE 8080

# Command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "-Duser.timezone=Asia/Seoul", "yomankum.jar"]
