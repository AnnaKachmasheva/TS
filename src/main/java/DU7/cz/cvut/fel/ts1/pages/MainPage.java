package DU7.cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[@id='header']/div[1]/div[1]/a")
    private WebElement loginBtn;

    @FindBy(how = How.XPATH, using = "//*[@id='search-options']/button")
    private WebElement optionSearchBtn;

    @FindBy(how = How.XPATH, using = "//*[@id='advanced-search-link']")
    private WebElement advancedSearchBtn;

    @FindBy(how = How.XPATH, using = "//*[@id='query']")
    private WebElement searchInput;

    @FindBy(how = How.XPATH, using = "//*[@id='search']")
    private WebElement btnSearch;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SingUpLoginPage clickLogin(String email, String password) {
        loginBtn.click();
        return new SingUpLoginPage(driver, email, password);
    }

    public AdvancedSearchPage clickGoToAdvancedSearch() {
        optionSearchBtn.click();
        advancedSearchBtn.click();
        return new AdvancedSearchPage(driver);
    }

    public SearchPage clickSearchByName(String title) {
        searchInput.sendKeys(title);
        btnSearch.click();
        return new SearchPage(driver);
    }
}