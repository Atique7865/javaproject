# Simple Java Web App

A small Spring Boot web application built with Maven. It exposes a browser page at `/`
and a JSON endpoint at `/api/message`.

## Project Details

- Java version: 17
- Build tool: Maven
- Framework: Spring Boot 3.3.5
- Artifact: executable jar
- Default port: 8080

## Requirements On Rocky Linux

Install Java 17 and Maven:

```bash
sudo dnf update -y
sudo dnf install -y java-17-openjdk java-17-openjdk-devel maven git
```

Check the installed versions:

```bash
java -version
mvn -version
```

If more than one Java version is installed, select Java 17:

```bash
sudo alternatives --config java
sudo alternatives --config javac
```

## Get The Project

Clone the repository, or copy this project folder to your Rocky Linux machine:

```bash
git clone <your-repository-url>
cd simple-java-web
```

If you copied the folder manually, enter the project directory that contains `pom.xml`:

```bash
cd /path/to/javaproject
```

## Maven Build Lifecycle

Run these commands from the directory that contains `pom.xml`.

### 1. Compile

Compiles the application source code:

```bash
mvn compile
```

### 2. Test

Runs the automated tests:

```bash
mvn test
```

### 3. Package

Builds the executable Spring Boot jar in the `target` directory:

```bash
mvn package
```

After this command, the jar should be available at:

```text
target/simple-java-web-0.0.1-SNAPSHOT.jar
```

### 4. Install

Builds the project and installs the jar into your local Maven repository:

```bash
mvn install
```

This is useful when another local Maven project needs to use this project as a
dependency.

### 5. Deploy

Deploy uploads the built artifact to a remote Maven repository. This project can
run without `mvn deploy`; deploy is only needed when you publish the artifact to
a repository such as Nexus, Artifactory, GitHub Packages, or another Maven
repository.

The current `pom.xml` does not contain a `<distributionManagement>` section, so
`mvn deploy` will fail until you configure a remote repository.

Example `pom.xml` configuration:

```xml
<distributionManagement>
    <repository>
        <id>releases</id>
        <url>https://your-maven-repository.example.com/releases</url>
    </repository>
    <snapshotRepository>
        <id>snapshots</id>
        <url>https://your-maven-repository.example.com/snapshots</url>
    </snapshotRepository>
</distributionManagement>
```

Then add credentials to `~/.m2/settings.xml` on the Rocky Linux machine:

```xml
<settings>
    <servers>
        <server>
            <id>snapshots</id>
            <username>your-username</username>
            <password>your-password-or-token</password>
        </server>
        <server>
            <id>releases</id>
            <username>your-username</username>
            <password>your-password-or-token</password>
        </server>
    </servers>
</settings>
```

The `<id>` values in `settings.xml` must match the `<id>` values in
`distributionManagement`.

After configuring the remote repository, run:

```bash
mvn deploy
```

## Run The Project

### Option 1: Run With Maven

For development:

```bash
mvn spring-boot:run
```

Open:

```text
http://localhost:8080
```

### Option 2: Run With The Jar File

First build the jar:

```bash
mvn clean package
```

Run it:

```bash
java -jar target/simple-java-web-0.0.1-SNAPSHOT.jar
```

Open:

```text
http://localhost:8080
```

Run on a different port:

```bash
java -jar target/simple-java-web-0.0.1-SNAPSHOT.jar --server.port=9090
```

Then open:

```text
http://localhost:9090
```

## Run In The Background On Rocky Linux

For a quick background run:

```bash
nohup java -jar target/simple-java-web-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

Check the process:

```bash
ps aux | grep simple-java-web
```

View logs:

```bash
tail -f app.log
```

Stop the app:

```bash
pkill -f simple-java-web-0.0.1-SNAPSHOT.jar
```

## Create A Systemd Service

For a server deployment, copy the jar to `/opt/simple-java-web`:

```bash
sudo mkdir -p /opt/simple-java-web
sudo cp target/simple-java-web-0.0.1-SNAPSHOT.jar /opt/simple-java-web/app.jar
```

Create a service file:

```bash
sudo vi /etc/systemd/system/simple-java-web.service
```

Add this content:

```ini
[Unit]
Description=Simple Java Web App
After=network.target

[Service]
User=root
WorkingDirectory=/opt/simple-java-web
ExecStart=/usr/bin/java -jar /opt/simple-java-web/app.jar
SuccessExitStatus=143
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

Start and enable the service:

```bash
sudo systemctl daemon-reload
sudo systemctl enable simple-java-web
sudo systemctl start simple-java-web
```

Check status and logs:

```bash
sudo systemctl status simple-java-web
sudo journalctl -u simple-java-web -f
```

Stop or restart:

```bash
sudo systemctl stop simple-java-web
sudo systemctl restart simple-java-web
```

## Firewall

If you want to access the app from another machine, allow port 8080:

```bash
sudo firewall-cmd --permanent --add-port=8080/tcp
sudo firewall-cmd --reload
```

## Endpoints

- `GET /` renders the web page.
- `GET /api/message` returns a JSON message.

## Useful Maven Commands

Clean old build files:

```bash
mvn clean
```

Clean and run tests:

```bash
mvn clean test
```

Clean, test, and package:

```bash
mvn clean package
```

Skip tests while packaging:

```bash
mvn package -DskipTests
```

Show dependency tree:

```bash
mvn dependency:tree
```

## Troubleshooting

If port 8080 is already in use:

```bash
sudo ss -ltnp | grep :8080
```

Then run on another port:

```bash
java -jar target/simple-java-web-0.0.1-SNAPSHOT.jar --server.port=9090
```

If Maven cannot find Java 17, confirm `JAVA_HOME`:

```bash
readlink -f $(which java)
```

Set `JAVA_HOME` temporarily:

```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH
```

If tests fail, run:

```bash
mvn test
```

Then read the test reports in:

```text
target/surefire-reports/
```
