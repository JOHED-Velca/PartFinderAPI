# Use a base image with Java
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy built JAR into the container
COPY target/partfinder-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
