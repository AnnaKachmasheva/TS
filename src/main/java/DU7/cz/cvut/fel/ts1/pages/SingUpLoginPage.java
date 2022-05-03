package DU7.cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SingUpLoginPage {

    private WebDriver driver;
    private String email;
    private String password;

    @FindBy(how = How.XPATH, using = "//*[@id='login-box-email']")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//*[@id='login-box-pw']")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//*[@id='login-box']/div/div[3]/button")
    private WebElement button;

    public SingUpLoginPage(WebDriver driver, String email, String password) {
        this.driver = driver;
        this.email = email;
        this.password = password;
        PageFactory.initElements(driver, this);
    }

    public MainPage clickBtnLogin() {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        button.click();
        return new MainPage(driver);
    }
}