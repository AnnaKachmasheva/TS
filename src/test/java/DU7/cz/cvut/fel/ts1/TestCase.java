package DU7.cz.cvut.fel.ts1;

import DU7.cz.cvut.fel.ts1.pages.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class TestCase {

    private static WebDriver driver;
    private static MainPage mainPage;

    @BeforeAll
    public static void init() {
        try {
            System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
            driver = new ChromeDriver();
            Dimension dimension = new Dimension(1920, 1080);
            driver.manage().window().setSize(dimension);
            driver.get("https://link.springer.com/");
            mainPage = new MainPage(getDriver())
                    .clickLogin(Constants.EMAIL, Constants.PASSWORD)
                    .clickBtnLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void gotoMainPage(){
        driver.get("https://link.springer.com/");
    }

    @AfterAll
    public static void clean() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static MainPage getMainPage() {
        return mainPage;
    }
}
