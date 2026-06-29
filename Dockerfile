FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy Maven wrapper and pom
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

RUN chmod +x mvnw
RUN ./mvnw -B dependency:go-offline

# Copy source code
COPY src/ src/

# Build and rename jar
RUN ./mvnw -B clean package -DskipTests && \
    JAR_FILE=$(find target -maxdepth 1 -type f -name '*.jar' ! -name '*.original' | head -n 1) && \
    cp "$JAR_FILE" target/app.jar

# Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the renamed jar
COPY --from=build /workspace/target/app.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]