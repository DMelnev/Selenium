/**
 * Class AuthorisationPage
 *
 * @author Melnev Dmitriy
 * @version 2022-05-26
 **/
package Lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorisationPage extends AbstractPage {

    public AuthorisationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//span[contains(.,'Вход')]")
    private WebElement input;

    @FindBy(xpath = ".//input[@id='auth-email']")
    private WebElement loginField;

    @FindBy(xpath = ".//input[@id='auth-pass']")
    private WebElement passField;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = ".//a[2]/span")
    private WebElement textView;

    @FindBy(xpath = "//button[@title='Close' and @class='fancybox-button fancybox-close-small']")
    private WebElement closeButton;

    public AuthorisationPage clickInput() {
        input.click();
        return this;
    }

    public AuthorisationPage fillFieldsAndClick(String login, String password) throws InterruptedException {
        loginField.sendKeys(login);
        passField.sendKeys(password);
        Thread.sleep(500);
        submitButton.click();
        Thread.sleep(500);
        return this;
    }

    public String getUsername() {
        return textView.getText();
    }

    public void closeForm() {
        closeButton.click();
    }
}
