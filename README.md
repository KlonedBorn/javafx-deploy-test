# JavaFX Deploy Test

## Table of Contents

- [About](#about)
- [Getting Started](#getting_started)
- [Usage](#usage)
- [Contributing](../CONTRIBUTING.md)

## About <a name = "about"></a>

This project is a JavaFX application that demonstrates how to deploy and run a JavaFX project using Maven. It includes all necessary dependencies and plugins for building, packaging, and running the application.

## Getting Started <a name = "getting_started"></a>

These instructions will get your JavaFX project up and running on your local machine for development and testing purposes.

### Prerequisites

You need to have the following installed:

- JDK 11 or higher
- Maven 3.6.0 or higher

To verify your installations, run the following commands in your terminal:

```bash
java -version
mvn -version
```

### Installing

Follow these steps to set up the project:

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/kloneborn/javafx-deploy-test.git
   cd javafx-deploy-test
   ```

2. Install dependencies and build the project:

   ```bash
   mvn install
   ```

3. Run the JavaFX application:
   ```bash
   mvn javafx:run
   ```

Once the project is built, a `.jar` file will be generated in the `/target/` directory.

### Script for Running

To make it easier to run the application, create a script file depending on your operating system:

- **Windows (.cmd):**

  ```cmd
  @echo off
  java -jar target\javafx-deploy-test-1.0-SNAPSHOT.jar
  ```

- **PowerShell (.ps1):**

  ```powershell
  java -jar .target\javafx-deploy-test-1.0-SNAPSHOT.jar
  ```

- **Linux/MacOS (.bash):**
  ```bash
  java -jar target/javafx-deploy-test-1.0-SNAPSHOT.jar
  ```

You will need to adjust the file based on your operating system to ensure the JAR file is properly located and executed.

## Usage <a name = "usage"></a>

To use the application, follow these instructions:

- Make sure you have run the project as described in the "Getting Started" section.
- If needed, modify the script for running the application on your specific system.

## Project Maven Configuration

Here is the `pom.xml` configuration for reference:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.kloneborn.github</groupId>
    <artifactId>javafx-deploy-test</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>22</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>22</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
                <configuration>
                    <release>11</release>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.8</version>
                <executions>
                    <execution>
                        <id>default-cli</id>
                        <configuration>
                            <mainClass>com.kloneborn.github/com.kloneborn.github.Launcher</mainClass>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.6.0</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <transformers>
                                <transformer
                                    implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>com.kloneborn.github.Launcher</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```
