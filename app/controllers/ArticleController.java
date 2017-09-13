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
import java.io.StringWriter;
import java.util.Collection;

public class ArticleController extends Controller {
    private DatabaseAdapter databaseAdapter;

    @Inject
    public ArticleController(DatabaseAdapter databaseAdapter) {
        this.databaseAdapter = databaseAdapter;
    }

    public Result list() throws IOException {
        final StringWriter stringWriter = new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();

        Collection<Article> articles = databaseAdapter.listArticles();
        mapper.writeValue(stringWriter, articles);

        return ok(stringWriter.toString());
    }

    public Result create() {
        final ObjectMapper mapper = new ObjectMapper();

        String uuid;
        try {
            JsonNode articleJson = mapper.readTree(request().body().asText());

            uuid = databaseAdapter.createArticle(articleJson.get("title").asText(),
                    articleJson.get("content").asText(), articleJson.get("author").asText());
        } catch (IOException e) {
            return badRequest();
        }

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

    public Result delete(String id) {
        return databaseAdapter.deleteArticle(id) ?
                ok(Json.parse("{\"message\": \"Removed successfully\"}")) :
                notFound(Json.parse("{\"error\": \"Article not found\"}"));
    }
}
