/**
 * Class AbstractPage
 *
 * @author Melnev Dmitriy
 * @version 2022-05-26
 **/
package Lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    private WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return this.driver;
    }

    protected void goToNextPage() {
        String originalWindow = getDriver().getWindowHandle();
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }
}
