package DU7.cz.cvut.fel.ts1;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class Case {

    private static WebDriver driver;

    public static void init() {
        try {
            System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
            driver = new ChromeDriver();
            Dimension dimension = new Dimension(1920, 1080);
            driver.manage().window().setSize(dimension);
            driver.get("https://link.springer.com/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clean() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}