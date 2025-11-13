# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Status

This is currently an empty Kotlin project repository containing only basic setup files (LICENSE, README, .gitignore). No build system or source code has been created yet.

## Setting Up a Kotlin Project

When initializing this repository, consider these common Kotlin project structures:

**Gradle (recommended):**
```bash
# Initialize with Gradle wrapper
gradle init --type kotlin-application
# Or for a library
gradle init --type kotlin-library
```

**Maven:**
```bash
mvn archetype:generate -DgroupId=com.example -DartifactId=hello-world-kotlin -DarchetypeArtifactId=maven-archetype-quickstart
```

## Expected Project Structure

Once initialized, the typical structure would be:

```
src/
  main/kotlin/     # Main source files
  test/kotlin/     # Test files
build.gradle.kts   # Gradle build configuration (Kotlin DSL)
# or build.gradle  # Gradle build configuration (Groovy)
# or pom.xml       # Maven configuration
```

## Common Commands (Once Build System is Set Up)

**With Gradle:**
- Build: `./gradlew build`
- Run: `./gradlew run`
- Test: `./gradlew test`
- Clean: `./gradlew clean`

**With Maven:**
- Build: `mvn compile`
- Run: `mvn exec:java`
- Test: `mvn test`
- Clean: `mvn clean`

## Kotlin-Specific Notes

- Kotlin files use `.kt` extension
- Main function signature: `fun main(args: Array<String>)` or `fun main()`
- The .gitignore is configured for Kotlin/JVM projects (excludes .class files, .kotlin/ directory, etc.)