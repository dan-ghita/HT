package database;

import models.*;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryHashDatabaseAdapter implements DatabaseAdapter {
    private ConcurrentHashMap<String, Article> storage = new ConcurrentHashMap<String, Article>();

    public String writeArticle(Article article) {
        UUID uuid = UUID.randomUUID();

        storage.put(uuid.toString(), article);

        return uuid.toString();
    }

    public Article readArticle(String articleId){
        return storage.get(articleId);
    }

    public InMemoryHashDatabaseAdapter() { }
}