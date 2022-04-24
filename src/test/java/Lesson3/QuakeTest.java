package Lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class QuakeTest {

    ChromeOptions options;
    WebDriver driver;
    String HOST = "https://quke.ru";
    String ORDER_PAGE = "https://quke.ru/order";
    String LOGIN = "gb2010@internet.ru";
    String PASSWORD = "oeAytr93APE?";
    String USERNAME = "Tester";
    String TEST_SEARCH = "Samsung Galaxy A52 128";
    String TEST_SEARCH_FAIL = "Samsung Galaxy A52 129";
    String TEST_PRODUCT = "Samsung Galaxy A52 128Gb Black";

    @Before
    public void before() {
        //        System.setProperty("webdriver.chrome.driver","D:\\System\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriverManager.chromedriver().setup(); //doesn't work on my office PC (((
        options = new ChromeOptions();
        options.addArguments("--incognito");
//        options.addArguments("--headless"); // hide mode
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(HOST);

    }

    @Test
    public void authorisation() throws InterruptedException {
        WebElement element1 = driver.findElement(By.xpath(".//span[contains(.,'Вход')]"));
        element1.click();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(driver1 -> driver1.findElement(By.cssSelector(".auth")));
            WebElement emailInput = driver.findElement(By.xpath(".//input[@id='auth-email']"));
            WebElement passInput = driver.findElement(By.xpath(".//input[@id='auth-pass']"));
            WebElement buttonSubmit = driver.findElement(By.xpath(".//button[@type='submit']"));
            emailInput.sendKeys(LOGIN);
            passInput.sendKeys(PASSWORD);
            Thread.sleep(500);
            buttonSubmit.click();

        } catch (TimeoutException e) {
            Assert.assertTrue("Authorisation form has not loaded. Timeout exception.", false);
        }
        Thread.sleep(1000);
        WebElement result = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> driver1.findElement(By.xpath(".//a[2]/span")));
        System.out.println(result.getText());
        Assert.assertEquals(USERNAME, result.getText());
    }

    @Test
    public void findProductBySubmit() throws InterruptedException {
        WebElement searchInput = driver.findElement(By.xpath(".//input[@id='main-search-input']"));
        searchInput.sendKeys(TEST_SEARCH);
        searchInput.submit();
        Thread.sleep(1000);
        List<WebElement> result = driver.findElements(By.xpath(".//a[contains(text(),'" + TEST_SEARCH + "')]"));
        Assert.assertTrue("Hasn't found some search result",result.size() > 0);
    }
}
