package mk.ukim.finki.fooddeliverybackend.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class HomePage extends AbstractPage {

    private static final String WELCOME_SECTION_SELECTOR = ".welcome-section";

    @FindBy(css = WELCOME_SECTION_SELECTOR)
    private WebElement welcomeSection;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public static HomePage to(WebDriver driver) {
        get(driver, "/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(DishesPage::isPageFullyLoaded);

        return PageFactory.initElements(driver, HomePage.class);
    }

    public static boolean isPageFullyLoaded(WebDriver driver) {
        return AbstractPage.areElementsPresentAndVisible(
                driver,
                WELCOME_SECTION_SELECTOR
        );
    }

}

