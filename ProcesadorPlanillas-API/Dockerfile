# Use the official Maven image as the base image
FROM alpine:3.14

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Copy the rest of the source code to the container
COPY src/ ./src/

# Build the application using Maven
RUN mvn clean package

# Copy the built JAR file from the build image to the runtime image
COPY --from=build /app/target/ProcesadorPlanillas-0.0.1-PGD.jar ./app.jar

# Expose port 8040 to the outside world
EXPOSE 8040

# Command to run the application
CMD ["mvn", "spring-boot:run"]