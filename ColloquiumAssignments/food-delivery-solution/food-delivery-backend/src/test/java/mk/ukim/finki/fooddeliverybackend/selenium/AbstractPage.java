package mk.ukim.finki.fooddeliverybackend.selenium;

import mk.ukim.finki.fooddeliverybackend.util.ExamAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void get(WebDriver driver, String relativeUrl) {
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:3000") + relativeUrl;
        driver.get(url);
    }

    public static void assertRelativeUrl(WebDriver driver, String relativeUrl) {
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:3000") + relativeUrl;
        String current = driver.getCurrentUrl();
        ExamAssert.assertUrlEquals("Requesting " + relativeUrl, url, current);
    }

    public static boolean assertAbsoluteUrl(WebDriver driver, String url) {
        String current = driver.getCurrentUrl();
        return ExamAssert.assertUrlEquals("Requesting " + url, url, current);
    }

    protected static boolean areElementsPresentAndVisible(WebDriver driver, String... selectors) {
        for (String selector : selectors) {
            List<WebElement> elements = driver.findElements(By.cssSelector(selector));
            if (elements.isEmpty())
                return false;
        }
        return true;
    }

}
