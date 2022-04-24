package Lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
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
    String LOGIN = "gb2010@internet.ru";
    String PASSWORD = "oeAytr93APE?";
    String USERNAME = "Tester";
    String TEST_SEARCH = "Samsung Galaxy A52 128";
    String TEST_SEARCH_FAIL = "Samsung Galaxy A52 129";
    String TEST_PRODUCT = "Samsung Galaxy A52 128Gb Black";
    String SUCCESSFUL_ORDER_RESULT = "Товар добавлен в корзину";

    @Before
    public void before() {
//        System.setProperty("webdriver.chrome.driver", "D:\\System\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriverManager.chromedriver().setup(); //doesn't work on my office PC ((( because program files there are not standard place. And How i can fix it, i don't know(((
        options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless"); // hide mode
//        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(HOST);

    }

    @After
    public void after() {
        driver.quit();
    }

    @Test
    public void authorisation() throws InterruptedException {
        driver.findElement(By.xpath(".//span[contains(.,'Вход')]")).click();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(driver1 -> driver1.findElement(By.cssSelector(".auth")));
            driver.findElement(By.xpath(".//input[@id='auth-email']")).sendKeys(LOGIN);
            driver.findElement(By.xpath(".//input[@id='auth-pass']")).sendKeys(PASSWORD);
            Thread.sleep(500);
            driver.findElement(By.xpath(".//button[@type='submit']")).click();
        } catch (TimeoutException e) {
            Assert.fail("Authorisation form has not loaded. Timeout exception.");
        }
        Thread.sleep(1000);
        WebElement result = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> driver1.findElement(By.xpath(".//a[2]/span")));
        Assert.assertEquals(USERNAME, result.getText());
    }

    @Test
    public void findProductBySubmit() throws InterruptedException {
        WebElement searchInput = driver.findElement(By.xpath(".//input[@id='main-search-input']"));
        searchInput.sendKeys(TEST_SEARCH);
        searchInput.submit();
        Thread.sleep(1000);
        List<WebElement> result = driver.findElements(By.xpath(".//a[contains(text(),'" + TEST_SEARCH + "')]"));
        Assert.assertTrue("Hasn't found some search result", result.size() > 0);
    }

    @Test
    public void findProductByClick() throws InterruptedException {
        driver.findElement(By.xpath(".//input[@id='main-search-input']")).sendKeys(TEST_SEARCH);
        driver.findElement(By.cssSelector(".h-search__icon")).click();
        Thread.sleep(1000);
        List<WebElement> result = driver.findElements(By.xpath(".//a[contains(text(),'" + TEST_SEARCH + "')]"));
        Assert.assertTrue("Hasn't found some search result", result.size() > 0);
    }

    @Test
    public void findNotPresentProduct() throws InterruptedException {
        WebElement searchInput = driver.findElement(By.xpath(".//input[@id='main-search-input']"));
        searchInput.sendKeys(TEST_SEARCH_FAIL);
        searchInput.submit();
        Thread.sleep(1000);
        List<WebElement> result = driver.findElements(By.xpath(".//a[contains(text(),'" + TEST_SEARCH_FAIL + "')]"));
        Assert.assertEquals("Has found some search result but don't must", 0, result.size());
    }

    @Test
    public void checkArticle() throws InterruptedException {
        driver.findElement(By.xpath(".//span[contains(.,'Вход')]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> driver1.findElement(By.cssSelector(".auth")));
        driver.findElement(By.xpath(".//input[@id='auth-email']")).sendKeys(LOGIN);
        driver.findElement(By.xpath(".//input[@id='auth-pass']")).sendKeys(PASSWORD);
        Thread.sleep(500);
        driver.findElement(By.xpath(".//button[@type='submit']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath(".//input[@id='main-search-input']")).sendKeys(TEST_SEARCH);
        driver.findElement(By.cssSelector(".h-search__icon")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath(".//a[contains(text(),'" + TEST_PRODUCT + "')]")).click();
        Thread.sleep(500);
        goToNextPage();
        WebElement buttonBuy = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> driver1.findElement(By.cssSelector(".btn--pink:nth-child(1) > .text")));
        buttonBuy.click();
        Thread.sleep(1000);
        WebElement result = driver.findElement(By.xpath("//div[@class=\"p-cart__title\"]/div[@class=\"text\"]"));
        Assert.assertEquals(SUCCESSFUL_ORDER_RESULT, result.getText());
    }

    private void goToNextPage() { //I guess that this method doesn't change tab in hide mode (ElementClickInterceptedException: element click intercepted: Element is not clickable at point (658, 625))
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
