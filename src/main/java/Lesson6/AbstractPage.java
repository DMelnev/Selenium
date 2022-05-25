/**
 * Class AbstractPage
 *
 * @author Melnev Dmitriy
 * @version 2022-
 **/
package Lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    static ChromeOptions options;
    private WebDriver driver;
    Actions actions;
    String HOST = "https://quke.ru";
    String LOGIN = "gb2010@internet.ru";
    String PASSWORD = "oeAytr93APE?";
    String USERNAME = "Tester";

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver(){
        return this.driver;
    }

}
