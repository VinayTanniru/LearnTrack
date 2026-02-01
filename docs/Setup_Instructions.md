# Setup Instructions

This document provides step-by-step instructions for setting up the Java Development Kit (JDK) and running the LearnTrack application.

## JDK Version Used

- **JDK Version:** 17 (LTS)
- **Vendor:** Oracle OpenJDK or Eclipse Temurin (AdoptOpenJDK)

## Step 1: Install Java Development Kit (JDK)

### Windows

1. **Download JDK:**
   - Visit [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/) or
   - [Eclipse Temurin (Free)](https://adoptium.net/)
   - Download JDK 17 or higher for Windows

2. **Run the installer:**
   - Execute the downloaded `.msi` or `.exe` file
   - Follow the installation wizard
   - Default installation path: `C:\Program Files\Java\jdk-17`

3. **Set Environment Variables:**
   - Open System Properties → Advanced → Environment Variables
   - Add `JAVA_HOME`:
     - Variable name: `JAVA_HOME`
     - Variable value: `C:\Program Files\Java\jdk-17`
   - Update `Path`:
     - Add `%JAVA_HOME%\bin` to the Path variable

4. **Verify Installation:**
   ```powershell
   java -version
   javac -version
   ```

   Expected output:
   ```
   java version "17.0.x" 2024-xx-xx LTS
   Java(TM) SE Runtime Environment (build 17.0.x+xx-xx)
   Java HotSpot(TM) 64-Bit Server VM (build 17.0.x+xx-xx, mixed mode, sharing)
   ```

## Step 2: Verify with "Hello World" Program

Create a simple test program to verify your setup:

### Create HelloWorld.java

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        System.out.println("Java is working correctly!");
        System.out.println("JDK Version: " + System.getProperty("java.version"));
    }
}
```

### Compile and Run

```powershell
# Navigate to the file location
cd C:\temp

# Compile the Java file
javac HelloWorld.java

# Run the compiled class
java HelloWorld
```

### Expected Output

```
Hello, World!
Java is working correctly!
JDK Version: 17.0.x
```

## Step 3: Compile and Run LearnTrack

### Using Command Line

```powershell
# Navigate to project directory
cd c:\Projects\Misc\LearnTrack

# Create output directory
mkdir -p bin

# Compile all source files
javac -d bin src/com/airtribe/learntrack/entity/*.java `
             src/com/airtribe/learntrack/exception/*.java `
             src/com/airtribe/learntrack/util/*.java `
             src/com/airtribe/learntrack/service/*.java `
             src/com/airtribe/learntrack/ui/*.java

# Run the application
java -cp bin com.airtribe.learntrack.ui.Main
```

### Using an IDE

#### IntelliJ IDEA
1. Open IntelliJ IDEA
2. File → Open → Select the LearnTrack folder
3. Right-click on `src` folder → Mark Directory as → Sources Root
4. Navigate to `Main.java`
5. Click the green "Run" button or press `Shift + F10`

#### Eclipse
1. Open Eclipse
2. File → Import → Existing Projects into Workspace
3. Select the LearnTrack folder
4. Right-click on `Main.java` → Run As → Java Application

## Common Issues and Solutions

### Issue: 'java' is not recognized

**Solution:** Ensure `JAVA_HOME` is set and `%JAVA_HOME%\bin` is in your PATH variable.

### Issue: Class not found error

**Solution:** Make sure you're running from the correct directory with the `-cp` flag pointing to the `bin` folder.

### Issue: Compilation errors

**Solution:** Ensure you're using JDK 11 or higher. Some features like `LocalDate` require Java 8+.

## Useful Commands

```powershell
# Check Java version
java -version

# Check compiler version
javac -version

# Check JAVA_HOME
echo $env:JAVA_HOME

# List all Java files in project
Get-ChildItem -Path src -Recurse -Filter *.java
```
