/**
 * Class Footer
 *
 * @author Melnev Dmitriy
 * @version 2022-05-26
 **/
package Lesson7;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Footer extends AbstractPage {

    public Footer(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//body")
    private WebElement body;

    @FindBy(xpath = "//span[@class='f-menu__title' and contains(text(),'Контакты')]")
    private List<WebElement> itemsContact;

    @FindBy(xpath = "//span[contains(text(),'(495) 225-95-02')]")
    private List<WebElement> salesPhone;

    @FindBy(xpath = "//span[contains(text(),'(495) 120-30-32')]")
    private List<WebElement> servicePhone;

    public Footer scrollToEnd() throws InterruptedException {
        body.sendKeys(Keys.CONTROL, Keys.END);
        Thread.sleep(500);
        return this;
    }

    public int getNumberOfItems() {
        return itemsContact.size();
    }

    public int getNumbersOfSalesPhones() {
        return salesPhone.size();
    }

    public int getNumbersOfServicePhones() {
        return servicePhone.size();
    }
}