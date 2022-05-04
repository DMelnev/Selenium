/**
 * Class Authorization
 *
 * @author Melnev Dmitriy
 * @version 2022-05-04
 **/
package Lesson5;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationTest extends PrepareTest {

    @Test
    @DisplayName("Проверка авторизации")
    public void authorisation() throws InterruptedException {
        driver.findElement(By.xpath(".//span[contains(.,'Вход')]")).click();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(driver1 -> driver1.findElement(By.cssSelector(".auth")));
            actions
                    .sendKeys(driver.findElement(By.xpath(".//input[@id='auth-email']")), LOGIN)
                    .sendKeys(driver.findElement(By.xpath(".//input[@id='auth-pass']")), PASSWORD)
                    .pause(500L)
                    .click(driver.findElement(By.xpath(".//button[@type='submit']")))
                    .build()
                    .perform();
        } catch (TimeoutException e) {
            assertFalse(false, "Authorisation form has not loaded. Timeout exception.");
        }
        Thread.sleep(1000);
        WebElement result = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> driver1.findElement(By.xpath(".//a[2]/span")));
        assertEquals(USERNAME, result.getText());
    }
}

