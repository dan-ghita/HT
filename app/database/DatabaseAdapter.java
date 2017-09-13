package database;

import models.Article;

import java.util.Collection;

public interface DatabaseAdapter {
    Collection<Article> listArticles();
    String createArticle(String title, String content, String author);
    Article readArticle(String articleId);
    boolean deleteArticle(String articleId);
}