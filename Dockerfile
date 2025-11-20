# Multi-stage build for Kotlin application
# This Dockerfile has two stages (BUILD and RUNTIME) to keep the final image small. The build stage has all the build
# tools, but the final image only has what's needed to run the app.

# BUILD
# We start with an image that has Gradle + JDK21, we need the full JDK to compile Kotlin code
FROM gradle:8.14-jdk21 AS build
#Sets the working directory inside the container, all subsequent commands run from here (like cd /app on the terminal)
WORKDIR /app

# Copy Gradle wrapper and configuration files
# Each COPY creates a cached layer, if the files don't change, Docker reuses this layer, so we should copy first files
# that won't likely change
COPY gradlew .
COPY gradle gradle
COPY settings.gradle.kts .
COPY app/build.gradle.kts app/

# Download dependencies (another caching layer)
# Without this, Docker would redownload dependencies every time you change code
# Don't start a background Gradle process
# Don't fail if this command errors
RUN ./gradlew dependencies --no-daemon || true

# Copy source code
# Copied last because source code changes the most
COPY app/src app/src

# Build the application and create distribution
# Creates a runnable distribution with start scripts
# Skip running tests
RUN ./gradlew installDist --no-daemon -x test

# RUNTIME
# Only needs JRE (Jave Runtime Environment), not full JDK
# alpine - tiny Linux distribution
# Final image is much smaller (no Gradle, source code, build tools)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the distribution from build stage
# Copies the distribution (binaries + libs)
COPY --from=build /app/app/build/install/app /app

# Expose the port your http4k server runs on
# Documentation only, does not open the port (that's done with docker run -p)
EXPOSE 9000

# Run the application
# Executes the start script created by installDist which sets up classpath and runs your Kotlin main function
CMD ["/app/bin/app"]