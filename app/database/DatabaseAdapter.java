package database;

import models.*;

public interface DatabaseAdapter {
    String writeArticle(Article article);
    Article readArticle(String articleId);
}