package Lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class TestGoogleSearch {
    @Test
    public void testSearchGoogle() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
        options.addArguments("--headless"); // hide mode
//        options.addArguments("start-maximized");
        WebDriver driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://google.com");

        WebElement webElement1 = driver.findElement(By.name("q"));
        WebElement webElement2 = driver.findElement(By.cssSelector("input.gLFyf.gsfi"));
        WebElement webElement3 = driver.findElement(By.xpath(".//input[@name='q']"));

        webElement1.click();

        //        try {
//            WebElement webElementError = driver.findElement(By.name("error"));
//        } catch (NoSuchElementException e) {
//            e.printStackTrace();
//        }
//        List<WebElement> webElements = driver.findElements(By.name("error"));
//        if (webElements.size() > 1) {
//            System.out.println("error is found");
//            //todo
//        }

        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver1 -> driver1.findElement(By.xpath(".//*[@id=\"gb\"]/div/div[1]/div/div[1]/a")));
        Assert.assertEquals(element.getText(),"Почта");
//        assertEquals()
        webElement2.sendKeys("Поиск зелибобы");

        driver.quit();
    }
}
