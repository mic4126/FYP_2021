package hk.cityu.cs.AiRegistry.FIT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

public class Utils {
    public final static String MANAGEMENT_SITE = "http:\\\\localhost:4200";

    public final static String PUBLIC_SITE = "http:\\\\localhost:8080";

    public static ChromeOptions ChromeOptions() {
        var option = new ChromeOptions();
        option.setImplicitWaitTimeout(Duration.ofSeconds(10));

        return option;
    }

    public static void login(String username, String password, WebDriver driver) {
        driver.get(Utils.MANAGEMENT_SITE);
        driver.findElement(By.xpath("""
                //*[@id="username"]""")).sendKeys(username);

        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("""
                //button[@type="submit"]""")).click();
    }

    public static void scrollToElement(WebDriver driver, WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        Thread.sleep(500);
    }

    public static void clearAndEnterString(WebElement element, String data) {
        element.clear();
        element.sendKeys(data);
    }

    public static File genTempFile(String prefix, String suffix, String data) throws IOException {
        var tempFile = File.createTempFile(prefix, suffix);
        var writer = new FileWriter(tempFile);
        writer.append(data);
        writer.close();
        return tempFile;

    }

}
