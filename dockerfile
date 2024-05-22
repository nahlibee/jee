# Use a base image with Java installed
FROM openjdk:11-jre

# Copy the JAR file into the image
COPY myapp.jar /app/myapp.jar

# Set the working directory
WORKDIR /app

# Command to run the application
CMD ["java", "-jar", "myapp.jar"]