/**
 * Class MainSearch
 *
 * @author Melnev Dmitriy
 * @version 2022-05-26
 **/
package Lesson7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static Lesson7.DataSearch.*;

public class MainSearch extends AbstractPage {

    public MainSearch(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//input[@id='main-search-input']")
    private WebElement searchField;

    @FindBy(css = ".h-search__icon")
    private WebElement searchButton;

    @FindBy(xpath = ".//a[contains(text(),'" + TEST_SEARCH + "')]")
    private List<WebElement> searchValidItems;

    @FindBy(xpath = ".//a[contains(text(),'" + TEST_SEARCH_FAIL + "')]")
    private List<WebElement> searchInValidItems;

    @FindBy(xpath = ".//a[contains(text(),'" + TEST_PRODUCT + "')]")
    private WebElement resultSearching;

    @FindBy(css = ".btn--pink:nth-child(1) > .text")
    private WebElement buyButton;

    @FindBy(xpath = "//div[@class=\"p-cart__title\"]/div[@class=\"text\"]")
    private WebElement result;

    public void toSearchByClick(String value) throws InterruptedException {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.sendKeys(value);
        searchButton.click();
        Thread.sleep(1000);
    }

    public MainSearch toSearchBySubmit(String value) throws InterruptedException {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.sendKeys(value);
        searchField.submit();
        Thread.sleep(1000);
        return this;
    }

    public int takeNumberValidItems() {
        return searchValidItems.size();
    }

    public int takeNumberInValidItems() {
        return searchInValidItems.size();
    }

    public MainSearch clickResult() throws InterruptedException {
        resultSearching.click();
        Thread.sleep(500);
        return this;
    }

    public MainSearch goToNext() {
        goToNextPage();
        return this;
    }

    public MainSearch clickBuy() throws InterruptedException {
        buyButton.click();
        Thread.sleep(1000);
        return this;
    }

    public String getTextResult() {
        return result.getText();
    }
}