/**
 * Class ArticleTest
 *
 * @author Melnev Dmitriy
 * @version 2022-05-04
 **/
package Lesson6;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleTest extends PrepareTest {

    String TEST_SEARCH = "Samsung Galaxy A52 128";
    String TEST_SEARCH_FAIL = "Samsung Galaxy A52 129";
    String TEST_PRODUCT = "Samsung Galaxy A52 128Gb Black";
    String SUCCESSFUL_ORDER_RESULT = "Товар добавлен в корзину";

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
    public void findProductByClick() {
        actions
                .sendKeys(driver.findElement(By.xpath(".//input[@id='main-search-input']")), TEST_SEARCH)
                .click(driver.findElement(By.cssSelector(".h-search__icon")))
                .pause(500L)
                .build()
                .perform();
        List<WebElement> result = driver.findElements(By.xpath(".//a[contains(text(),'" + TEST_SEARCH + "')]"));
        assertTrue(result.size() > 0, "Hasn't found some search result");
    }

    @Test
    @DisplayName("Поиск отсутствующего товара")
    public void findNotPresentProduct() throws InterruptedException {
        actions
                .sendKeys(driver.findElement(By.xpath(".//input[@id='main-search-input']")), TEST_SEARCH_FAIL)
                .click(driver.findElement(By.cssSelector(".h-search__icon")))
                .pause(500L)
                .build()
                .perform();
        List<WebElement> result = driver.findElements(By.xpath(".//a[contains(text(),'" + TEST_SEARCH_FAIL + "')]"));
        assertEquals(0, result.size(), "Has found some search result but don't must");
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    public void checkArticle() throws InterruptedException {
        if (!authorization(LOGIN, PASSWORD, USERNAME)) fail("Cannot log in");

        actions
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
}
