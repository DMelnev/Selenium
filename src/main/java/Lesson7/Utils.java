/**
 * Class Utils
 *
 * @author Melnev Dmitriy
 * @version 2022-06-06
 **/
package Lesson7;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Utils {
    public static File makeScreenshot(WebDriver driver, String filename){
        File temp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File("./target/" + filename);
        try{
            FileUtils.copyFile(temp, destination);
        } catch (IOException e){
            e.printStackTrace();
        }
        return destination;
    }
}
