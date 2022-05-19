/**
 * Class FooterTest
 *
 * @author Melnev Dmitriy
 * @version 2022-05-04
 **/
package Lesson6;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FooterTest extends PrepareTest {

    @Test
    @DisplayName("Меню футера, контакты")
    public void checkFooter() throws InterruptedException {
        driver.findElement(By.xpath(".//body")).sendKeys(Keys.CONTROL, Keys.END);
        Thread.sleep(500);
        List<WebElement> result = driver.findElements(By.xpath("//span[@class='f-menu__title' and contains(text(),'Контакты')]"));
        assertTrue(result.size() > 0, "Has not found menu \"Контакты\"");
    }

    @Test
    @DisplayName("Телефон Интернет Магазина")
    public void checkPhoneOnlineStore(){
        List<WebElement> result = driver.findElements(By.xpath("//span[contains(text(),'(495) 225-95-02')]"));
        assertTrue(result.size() > 0, "Has not found phone \"Интернет-магазин\"");
    }

    @Test
    @DisplayName("Телефон Сервисного отдела")
    public void checkPhoneServices(){
        List<WebElement> result = driver.findElements(By.xpath("//span[contains(text(),'(495) 120-30-32')]"));
        assertTrue(result.size() > 0, "Has not found phone \"Сервисный отдел\"");
    }

}
