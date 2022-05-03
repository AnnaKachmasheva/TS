package DU7.cz.cvut.fel.ts1;

import DU7.cz.cvut.fel.ts1.model.Article;
import DU7.cz.cvut.fel.ts1.pages.MainPage;
import DU7.cz.cvut.fel.ts1.pages.SearchPage;
import org.openqa.selenium.WebElement;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class Selenium extends Case {

    public static void main(String[] args) {

        init();

        MainPage main = new MainPage(getDriver());
        SearchPage searchPage = main.clickLogin(Constants.EMAIL, Constants.PASSWORD)
                .clickBtnLogin()
                .clickGoToAdvancedSearch()
                .submitSearchForm(Constants.REQUIRED_WORDS, Constants.NOT_REQUIRED_WORDS, Constants.SELECT_OPTION, Constants.START_YEAR)
                .getOnlyArticles();

        List<WebElement> titleLinks = searchPage.getArticles();
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            articles.add(searchPage.clickLinkArticle(titleLinks.get(i)).createArticle());
        }
        saveResult(articles);

        clean();
    }

    private static void saveResult(List<Article> articles) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("result/result.txt"),
                    StandardCharsets.UTF_8));
            for (Article article : articles) {
                out.write(article + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}