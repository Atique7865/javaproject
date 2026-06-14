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
                    <title>Japan in Bloom | Travel Blog</title>
                    <link rel="stylesheet" href="/styles.css">
                </head>
                <body>
                    <header class="hero">
                        <nav class="nav" aria-label="Main navigation">
                            <a href="/" class="brand">Japan in Bloom</a>
                            <div class="nav-links">
                                <a href="#stories">Stories</a>
                                <a href="#journal">Journal</a>
                                <a href="/api/message">API</a>
                            </div>
                        </nav>
                        <section class="hero-content">
                            <p class="eyebrow">Travel stories from Japan</p>
                            <h1>The quiet beauty of Japan, one season at a time.</h1>
                            <p class="lead">
                                A visual blog about cedar forests, lantern-lit streets, snow country,
                                spring blossoms, and the everyday rituals that make Japan unforgettable.
                            </p>
                        </section>
                    </header>
                    <main>
                        <section class="intro" aria-label="Featured note">
                            <div>
                                <p class="eyebrow">Latest note</p>
                                <h2>Where old paths still feel alive</h2>
                            </div>
                            <p>
                                From Kyoto's shaded temple lanes to Hakone's mountain air, Japan rewards
                                travelers who slow down, look closely, and let each place reveal its rhythm.
                            </p>
                            <div class="status">
                                <span>Updated</span>
                                <strong>%s</strong>
                            </div>
                        </section>

                        <section id="stories" class="stories" aria-label="Featured Japan stories">
                            <article class="story story-large">
                                <img src="https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?auto=format&fit=crop&w=1200&q=80" alt="Traditional pagoda framed by autumn color in Kyoto">
                                <div class="story-body">
                                    <p class="category">Kyoto</p>
                                    <h2>Autumn light over temple roofs</h2>
                                    <p>Maples turn the old capital into a warm mosaic of gates, gardens, and quiet stone paths.</p>
                                </div>
                            </article>
                            <article class="story">
                                <img src="https://images.unsplash.com/photo-1524413840807-0c3cb6fa808d?auto=format&fit=crop&w=900&q=80" alt="Mount Fuji rising beyond a calm lake">
                                <div class="story-body">
                                    <p class="category">Fuji Five Lakes</p>
                                    <h2>Morning beside Fuji</h2>
                                    <p>Blue hour turns the mountain into a silhouette before the first trains begin to move.</p>
                                </div>
                            </article>
                            <article class="story">
                                <img src="https://images.unsplash.com/photo-1545569341-9eb8b30979d9?auto=format&fit=crop&w=900&q=80" alt="Red torii gates forming a tunnel in Japan">
                                <div class="story-body">
                                    <p class="category">Fushimi Inari</p>
                                    <h2>Walking through vermilion gates</h2>
                                    <p>The climb is a rhythm of shrine bells, forest shade, and flashes of red lacquer.</p>
                                </div>
                            </article>
                        </section>

                        <section id="journal" class="journal" aria-label="Recent blog posts">
                            <div class="section-heading">
                                <p class="eyebrow">Journal</p>
                                <h2>Seasonal impressions</h2>
                            </div>
                            <div class="post-list">
                                <article>
                                    <span>Spring</span>
                                    <h3>Cherry blossoms along the Meguro River</h3>
                                    <p>Soft petals, paper lanterns, and the hush that arrives when the water fills with pink.</p>
                                </article>
                                <article>
                                    <span>Summer</span>
                                    <h3>Rain on moss gardens in Arashiyama</h3>
                                    <p>Fresh green textures, bamboo shadows, and tea rooms that feel sheltered from time.</p>
                                </article>
                                <article>
                                    <span>Winter</span>
                                    <h3>Snow country evenings in Shirakawa-go</h3>
                                    <p>Thatched farmhouses glow against deep snow while the valley settles into silence.</p>
                                </article>
                            </div>
                        </section>
                    </main>
                </body>
                </html>
                """.formatted(LocalDateTime.now().format(FORMATTER));
    }

    @GetMapping("/api/message")
    public Message message() {
        return new Message("Japan in Bloom shares travel stories about the beauty of Japan.", LocalDateTime.now().format(FORMATTER));
    }

    public record Message(String text, String serverTime) {
    }
}
