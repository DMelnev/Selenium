package Lesson5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FooterTest extends PrepareTest {

    @Test
    @DisplayName("Меню футера, контакты")
    public void checkFooter() throws InterruptedException {
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        long windowWidth = (long) jsExecutor.executeScript("return window.innerWidth");
//        System.out.println("Window width " + windowWidth);
//        jsExecutor.executeScript("window.scrollBy(0,500)");
//        Thread.sleep(1000);
//        driver.findElement(By.xpath(".//body")).sendKeys(Keys.CONTROL, Keys.END);
//        Thread.sleep(1000);
        List<WebElement> result = driver.findElements(By.xpath("//span[@class='f-menu__title' and contains(text(),'Контакты')]"));
        assertTrue(result.size() > 0, "Has not found menu \"Контакты\"");
    }
}