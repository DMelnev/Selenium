/**
 * Class QuakeTestAuthorization
 *
 * @author Melnev Dmitriy
 * @version 2022-05-04
 **/
package Lesson5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class QuakeTestAuthorization extends PrepareTest {
    String TEST_SEARCH = "Samsung Galaxy A52 128";
    String TEST_SEARCH_FAIL = "Samsung Galaxy A52 129";
    String TEST_PRODUCT = "Samsung Galaxy A52 128Gb Black";
    String SUCCESSFUL_ORDER_RESULT = "Товар добавлен в корзину";

    @Test
    @DisplayName("Проверка авторизации")
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
            assertFalse(false, "Authorisation form has not loaded. Timeout exception.");
        }
        Thread.sleep(1000);
        WebElement result = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> driver1.findElement(By.xpath(".//a[2]/span")));
        assertEquals(USERNAME, result.getText());
    }

    @Test
    @DisplayName("Поиск продукта нажатием Enter")
    public void findProductBySubmit() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("https://quke.ru"));
        WebElement searchInput = driver.findElement(By.xpath(".//input[@id='main-search-input']"));
        searchInput.sendKeys(TEST_SEARCH);
        searchInput.submit();
        Thread.sleep(1000);
        List<WebElement> result = driver.findElements(By.xpath(".//a[contains(text(),'" + TEST_SEARCH + "')]"));
        assertTrue(result.size() > 0, "Hasn't found some search result");
    }

    @Test
    @DisplayName("Поиск продукта нажатием ЛКМ")
    public void findProductByClick() throws InterruptedException {
        driver.findElement(By.xpath(".//input[@id='main-search-input']")).sendKeys(TEST_SEARCH);
        driver.findElement(By.cssSelector(".h-search__icon")).click();
        Thread.sleep(1000);
        List<WebElement> result = driver.findElements(By.xpath(".//a[contains(text(),'" + TEST_SEARCH + "')]"));
        assertTrue(result.size() > 0, "Hasn't found some search result");
    }

    @Test
    @DisplayName("Поиск отсутствующего товара")
    public void findNotPresentProduct() throws InterruptedException {
        WebElement searchInput = driver.findElement(By.xpath(".//input[@id='main-search-input']"));
        searchInput.sendKeys(TEST_SEARCH_FAIL);
        searchInput.submit();
        Thread.sleep(1000);
        List<WebElement> result = driver.findElements(By.xpath(".//a[contains(text(),'" + TEST_SEARCH_FAIL + "')]"));
        assertEquals(0, result.size(), "Has found some search result but don't must");
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    public void checkArticle() throws InterruptedException {
        driver.findElement(By.xpath(".//span[contains(.,'Вход')]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> driver1.findElement(By.cssSelector(".auth")));
        Actions searchInput = new Actions(driver);
        searchInput
                .sendKeys(driver.findElement(By.xpath(".//input[@id='auth-email']")), LOGIN)
                .sendKeys(driver.findElement(By.xpath(".//input[@id='auth-pass']")), PASSWORD)
                .pause(500L)
                .click(driver.findElement(By.xpath(".//button[@type='submit']")))
                .pause(500L)
                .build()
                .perform();
        searchInput
                .sendKeys(driver.findElement(By.xpath(".//input[@id='main-search-input']")), TEST_SEARCH)
                .click(driver.findElement(By.cssSelector(".h-search__icon")))
                .pause(500L)
                .build()
                .perform();
        driver.findElement(By.xpath(".//a[contains(text(),'" + TEST_PRODUCT + "')]")).click();
        Thread.sleep(500);
        goToNextPage();
        WebElement buttonBuy = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(driver1 -> driver1.findElement(By.cssSelector(".btn--pink:nth-child(1) > .text")));
        buttonBuy.click();
        Thread.sleep(1000);
        WebElement result = driver.findElement(By.xpath("//div[@class=\"p-cart__title\"]/div[@class=\"text\"]"));
        assertEquals(SUCCESSFUL_ORDER_RESULT, result.getText());
    }

    private void goToNextPage() {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
