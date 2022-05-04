/**
 * Class PrepareTest
 *
 * @author Melnev Dmitriy
 * @version 2022-05-04
 **/
package Lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public abstract class PrepareTest {

    static ChromeOptions options;
    static WebDriver driver;
    String HOST = "https://quke.ru";
    String LOGIN = "gb2010@internet.ru";
    String PASSWORD = "oeAytr93APE?";
    String USERNAME = "Tester";

    @BeforeAll
    public static void beforeAll() {

//        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "D:\\System\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

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
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }
}
