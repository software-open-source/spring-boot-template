# Use Eclipse Temurin JRE for smaller image size
FROM eclipse-temurin:21-jre-alpine

# Set working directory
WORKDIR /app

# Install curl and create non-root user
RUN apk add --no-cache curl && \
    addgroup -g 1001 -S spring && \
    adduser -S spring -G spring -u 1001

# Copy the jar file
COPY target/spring-boot-template.jar app.jar

# Change ownership to spring user
RUN chown spring:spring app.jar

# Switch to non-root user
USER spring

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]