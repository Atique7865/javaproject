package com.example.webapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/")
    public String home() {
        return """
                <!doctype html>
                <html lang="en">
                <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Simple Java Web App</title>
                    <link rel="stylesheet" href="/styles.css">
                </head>
                <body>
                    <main class="shell">
                        <section class="panel">
                            <p class="eyebrow">Spring Boot</p>
                            <h1>Simple Java Web App</h1>
                            <p class="lead">
                                This page is served by a Java controller and styled with a static CSS file.
                            </p>
                            <div class="status">
                                <span>Server time</span>
                                <strong>%s</strong>
                            </div>
                            <a class="button" href="/api/message">View JSON API</a>
                        </section>
                    </main>
                </body>
                </html>
                """.formatted(LocalDateTime.now().format(FORMATTER));
    }

    @GetMapping("/api/message")
    public Message message() {
        return new Message("Hello from Java and Spring Boot!", LocalDateTime.now().format(FORMATTER));
    }

    public record Message(String text, String serverTime) {
    }
}
