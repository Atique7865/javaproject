# Simple Java Web App

A small web based Java project using Spring Boot.

## Requirements

- Java 17 or newer
- Maven 3.9 or newer

## Run

```powershell
mvn spring-boot:run
```

Open `http://localhost:8080` in your browser.

## Test

```powershell
mvn test
```

## Endpoints

- `GET /` renders the web page.
- `GET /api/message` returns a JSON message.
