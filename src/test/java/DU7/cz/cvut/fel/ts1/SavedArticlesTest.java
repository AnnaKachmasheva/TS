package DU7.cz.cvut.fel.ts1;

import DU7.cz.cvut.fel.ts1.model.Article;
import DU7.cz.cvut.fel.ts1.pages.ArticlePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SavedArticlesTest extends TestCase {

    @ParameterizedTest
    @MethodSource("articles")
    void checkData_IsValid_true(String title, String DOI, String date) {

        ArticlePage page = getMainPage()
                        .clickSearchByName(title)
                        .getFirstResult();

        Assertions.assertEquals(page.getDate(), date);
        Assertions.assertEquals(page.getDOI(), DOI);
    }

    private static Stream<Arguments> articles() {
        List<Article> articles = readSavedArticles();
        return Stream.of(
                arguments(articles.get(0).getName(), articles.get(0).getDOI(), articles.get(0).getDate()),
                arguments(articles.get(1).getName(), articles.get(1).getDOI(), articles.get(1).getDate()),
                arguments(articles.get(2).getName(), articles.get(2).getDOI(), articles.get(2).getDate()),
                arguments(articles.get(3).getName(), articles.get(3).getDOI(), articles.get(3).getDate())
        );
    }

    public static List<Article> readSavedArticles() {
        List<Article> articles = new ArrayList<>();
        try (FileReader reader = new FileReader("result/result.txt"); BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(", ");
                articles.add(new Article(arr[0], arr[1], arr[2]));
            }
            System.out.println(articles);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return articles;
    }
}