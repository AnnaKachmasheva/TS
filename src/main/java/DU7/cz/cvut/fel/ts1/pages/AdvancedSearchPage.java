package DU7.cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvancedSearchPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[@id='all-words']")
    private WebElement allWordsInput;

    @FindBy(how = How.XPATH, using = "//*[@id='least-words']")
    private WebElement leastOneOfTheWordsInput;

    @FindBy(how = How.XPATH, using = "//*[@id='date-facet-mode']")
    private WebElement yearSelect;

    @FindBy(how = How.XPATH, using = "//*[@id='facet-start-year']")
    private WebElement startYearInput;

    @FindBy(how = How.XPATH, using = "//*[@id='submit-advanced-search']")
    private WebElement formSearchBtn;

    public AdvancedSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchPage submitSearchForm(String allWords, String leastOneOfTheWords, String selectOption, String startYear) {
        allWordsInput.sendKeys(allWords);
        leastOneOfTheWordsInput.sendKeys(leastOneOfTheWords);
        Select select =  new Select(yearSelect);
        select.selectByValue(selectOption);
        startYearInput.sendKeys(startYear);
        formSearchBtn.click();
        return new SearchPage(driver);
    }
}
