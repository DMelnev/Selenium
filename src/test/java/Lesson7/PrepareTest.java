/**
 * Class PrepareTest
 *
 * @author Melnev Dmitriy
 * @version 2022-06-06
 **/
package Lesson7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;
import java.util.List;

import static Lesson7.DataAuthorisation.*;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class PrepareTest {

    static ChromeOptions options;
    //    static WebDriver driver;
    static EventFiringWebDriver driver;


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
//        driver = new ChromeDriver(options);
        driver = new EventFiringWebDriver(new ChromeDriver(options));
        driver.register(new MyDriverEventListener());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(HOST);
    }

    @AfterEach
    public void afterEach() {
        List<LogEntry> allLogRows = getDriver().manage().logs().get(LogType.BROWSER).getAll();
        if (!allLogRows.isEmpty()) {
            for (LogEntry entry : allLogRows) {
                System.out.println(entry.getMessage());
            }
        }
        driver.quit();
    }

    public boolean authorization(String login, String pass) throws InterruptedException {
        return authorization(login, pass, USERNAME);
    }

    public boolean authorization(String login, String pass, String username) throws InterruptedException {
        AuthorisationPage page = new AuthorisationPage(getDriver());
        try {
            page
                    .clickInput()
                    .fillFieldsAndClick(login, pass);
        } catch (TimeoutException e) {
            fail("Authorisation form has not loaded. Timeout exception.");
        }

        if (!username.equals(page.getUsername())) {
            Thread.sleep(500);
            page.closeForm();
            return false;
        }
        return true;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}