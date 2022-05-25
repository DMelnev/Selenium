package Lesson6;
/**
 * Class MainPage
 *
 * @author Melnev Dmitriy
 * @version 2022-
 **/
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class MainPage {

    public class MainMenu extends AbstractPage {

        @FindBy(xpath = ".//ul[@class='menu']/li//a[@href='/abit/']")
        private WebElement abit;
}
