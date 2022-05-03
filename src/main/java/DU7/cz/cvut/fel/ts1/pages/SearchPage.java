package DU7.cz.cvut.fel.ts1.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[@id='content-type-facet']/ol/li[2]/a")
    private WebElement selectArticles;

    @FindBy(css = "ol#results-list > li > h2 > a.title")
    private List<WebElement> resultList;

    @FindBy(css = "ol#results-list > li > h2 > a.title")
    private WebElement firstResult;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchPage getOnlyArticles() {
        selectArticles.click();
        return new SearchPage(driver);
    }

    public List<WebElement> getArticles() {
        return resultList;
    }

    public ArticlePage clickLinkArticle(WebElement link) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", link);
        return new ArticlePage(driver);
    }

    public ArticlePage getFirstResult() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", firstResult);
        return new ArticlePage(driver);
    }
}