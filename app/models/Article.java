package models;

import java.util.UUID;

public class Article {
    private UUID uuid;
    private String title;
    private String content;
    private String author;

    public Article(UUID uuid, String title, String content, String author) {
        this.uuid = uuid;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public UUID getUuid() { return uuid; }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}