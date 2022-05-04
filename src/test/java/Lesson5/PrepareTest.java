/**
 * Class PrepareTest
 *
 * @author Melnev Dmitriy
 * @version 2022-05-04
 **/
package Lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class PrepareTest {

    static ChromeOptions options;
    static WebDriver driver;
    Actions actions;
    String HOST = "https://quke.ru";
    String LOGIN = "gb2010@internet.ru";
    String PASSWORD = "oeAytr93APE?";
    String USERNAME = "Tester";

    @BeforeAll
    public static void beforeAll() {

        WebDriverManager.chromedriver().setup();
//        System.setProperty("webdriver.chrome.driver", "D:\\System\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        options = new ChromeOptions();
        options.addArguments("--incognito");
//        options.addArguments("--headless"); // Hide mode
        options.addArguments("start-maximized");

    }

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(HOST);
        actions = new Actions(driver);
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    public boolean authorization(String login, String pass) throws InterruptedException {
        return authorization(login, pass, USERNAME);
    }

    public boolean authorization(String login, String pass, String username) throws InterruptedException {
        driver.findElement(By.xpath(".//span[contains(.,'Вход')]")).click();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(driver1 -> driver1.findElement(By.cssSelector(".auth")));
            actions
                    .sendKeys(driver.findElement(By.xpath(".//input[@id='auth-email']")), login)
                    .sendKeys(driver.findElement(By.xpath(".//input[@id='auth-pass']")), pass)
                    .pause(500L)
                    .click(driver.findElement(By.xpath(".//button[@type='submit']")))
                    .build()
                    .perform();
        } catch (TimeoutException e) {
            assertFalse(false, "Authorisation form has not loaded. Timeout exception.");
        }
        Thread.sleep(500);
        WebElement result = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> driver1.findElement(By.xpath(".//a[2]/span")));

        if (!username.equals(result.getText())) {
            Thread.sleep(500);
            driver.findElement(By.xpath("//button[@title='Close' and @class='fancybox-button fancybox-close-small']")).click();
            return false;
        }
        return true;
    }
}
