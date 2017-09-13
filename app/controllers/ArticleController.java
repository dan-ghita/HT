package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.DatabaseAdapter;
import models.Article;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.IOException;

public class ArticleController extends Controller {
    private DatabaseAdapter databaseAdapter;

    @Inject
    public ArticleController(DatabaseAdapter databaseAdapter) {
        this.databaseAdapter = databaseAdapter;
    }

    public Result create() {
        ObjectMapper mapper = new ObjectMapper();

        Article article;

        try {
            JsonNode articleJson = mapper.readTree(request().body().asText());

            System.out.printf(articleJson.get("title").toString());

            article = new Article(articleJson.get("title").asText(),
                    articleJson.get("content").asText(), articleJson.get("author").asText());
        } catch (IOException e) {
            return badRequest();
        }

        String uuid = databaseAdapter.writeArticle(article);

        return ok(uuid);
    }

    public Result read(String id) {
        Article article = databaseAdapter.readArticle(id);

        if (article == null) {
            return notFound(Json.parse("{\"error\": \"Not found\"}"));
        }

        return ok(views.html.readarticle.render(article));
    }

    public Result update() {
        return ok();
    }

    public Result delete() {
        return ok();
    }
}
