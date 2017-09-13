package database;

import models.Article;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryHashDatabaseAdapter implements DatabaseAdapter {
    private ConcurrentHashMap<String, Article> storage = new ConcurrentHashMap<String, Article>();

    @Override
    public Collection<Article> listArticles() {
        return storage.values();
    }

    public String createArticle(String title, String content, String author) {
        UUID uuid = UUID.randomUUID();

        storage.put(uuid.toString(), new Article(uuid, title, content, author));

        return uuid.toString();
    }

    public Article readArticle(String articleId){
        return storage.get(articleId);
    }

    @Override
    public boolean deleteArticle(String articleId) {
        Article article = storage.remove(articleId);

        return article != null;
    }

    public InMemoryHashDatabaseAdapter() { }
}