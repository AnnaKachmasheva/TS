package DU7.cz.cvut.fel.ts1.pages;

import DU7.cz.cvut.fel.ts1.model.Article;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ArticlePage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[@id='main-content']/main/article/div[1]/header/h1")
    private WebElement nameArticle;

    @FindBy(css = ".c-bibliographic-information__list-item--doi .c-bibliographic-information__value")
    private WebElement DOIArticle;

    @FindBy(how = How.XPATH, using = "//*[@id='logo']/picture/img")
    private WebElement logoImage;

    @FindBy(css = "header time")
    private WebElement datePublishedArticle;

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Article createArticle() {
        Article article = new Article(nameArticle.getText(), DOIArticle.getText(), datePublishedArticle.getText());
        driver.navigate().back();
        return article;
    }

    public String getDOI() {
        return DOIArticle.getText();
    }

    public String getDate() {
        return datePublishedArticle.getText();
    }
}