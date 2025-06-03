package mk.ukim.finki.fooddeliverybackend.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class LoginPage extends AbstractPage {

    private static final String USERNAME_FIELD_SELECTOR = "input[name='username']";
    private static final String PASSWORD_FIELD_SELECTOR = "input[name='password']";
    private static final String SUBMIT_BUTTON_SELECTOR = ".submit-btn";

    @FindBy(css = USERNAME_FIELD_SELECTOR)
    private WebElement usernameField;

    @FindBy(css = PASSWORD_FIELD_SELECTOR)
    private WebElement passwordField;

    @FindBy(css = SUBMIT_BUTTON_SELECTOR)
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static HomePage login(WebDriver driver, String username, String password) {
        get(driver, "/login");

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(LoginPage::isPageFullyLoaded);

        loginPage.usernameField.sendKeys(username);
        loginPage.passwordField.sendKeys(password);
        loginPage.submitButton.click();

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        wait.until(HomePage::isPageFullyLoaded);

        return homePage;
    }

    public static boolean isPageFullyLoaded(WebDriver driver) {
        return AbstractPage.areElementsPresentAndVisible(
                driver,
                USERNAME_FIELD_SELECTOR,
                PASSWORD_FIELD_SELECTOR,
                SUBMIT_BUTTON_SELECTOR
        );
    }

}
