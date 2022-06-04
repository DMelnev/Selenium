/**
 * Class FooterTest
 *
 * @author Melnev Dmitriy
 * @version 2022-05-26
 **/
package Lesson7;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class FooterTest extends PrepareTest {
    Footer footer;

    @BeforeEach
    public void createPage() {
        footer = new Footer(getDriver());
    }

    @Test
    @DisplayName("Меню футера, контакты")
    public void checkFooter() throws InterruptedException {
        footer.scrollToEnd();
        assertTrue(footer.getNumberOfItems() > 0, "Has not found menu \"Контакты\"");
    }

    @Test
    @DisplayName("Телефон Интернет Магазина")
    public void checkPhoneOnlineStore() {
        assertTrue(footer.getNumbersOfSalesPhones() > 0, "Has not found phone \"Интернет-магазин\"");
    }

    @Test
    @DisplayName("Телефон Сервисного отдела")
    public void checkPhoneServices() {
        assertTrue(footer.getNumbersOfServicePhones() > 0, "Has not found phone \"Сервисный отдел\"");
    }

}