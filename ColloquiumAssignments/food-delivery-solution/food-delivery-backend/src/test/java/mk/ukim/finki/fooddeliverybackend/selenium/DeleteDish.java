package mk.ukim.finki.fooddeliverybackend.selenium;

import lombok.Getter;
import mk.ukim.finki.fooddeliverybackend.dto.domain.CreateDishDto;
import mk.ukim.finki.fooddeliverybackend.util.ExamAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class DeleteDish extends AbstractPage {

    private static final String CARD_SELECTOR = ".card";
    private static final String SUBMIT_BUTTON_SELECTOR = ".submit-btn";

    @FindBy(css = CARD_SELECTOR)
    private List<WebElement> cards;

    @FindBy(css = SUBMIT_BUTTON_SELECTOR)
    private WebElement submitButton;

    public DeleteDish(WebDriver driver) {
        super(driver);
    }

    public static DeleteDish delete(WebDriver driver, Long dishId) {
        get(driver, "/dishes");
        assertRelativeUrl(driver, "/dishes");

        DeleteDish deleteDish = PageFactory.initElements(driver, DeleteDish.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(DeleteDish::isPageFullyLoaded);

        String deleteButtonSelector = String.format(".card[data-id='%s'] .delete-item", dishId);
        WebElement deleteButton = driver.findElement(By.cssSelector(deleteButtonSelector));

        deleteButton.click();

        deleteDish = PageFactory.initElements(driver, DeleteDish.class);
        wait.until(DeleteDish::isFormFullyLoaded);

        deleteDish.submitButton.click();

        deleteDish = PageFactory.initElements(driver, DeleteDish.class);
        wait.until(d -> !isFormFullyLoaded(driver));

        return deleteDish;
    }

    private static boolean isPageFullyLoaded(WebDriver driver) {
        return AbstractPage.areElementsPresentAndVisible(
                driver,
                CARD_SELECTOR
        );
    }

    private static boolean isFormFullyLoaded(WebDriver driver) {
        return AbstractPage.areElementsPresentAndVisible(
                driver,
                SUBMIT_BUTTON_SELECTOR
        );
    }

    public void assertItems(int expectedItemsNumber) {
        ExamAssert.assertEquals("Number of items", expectedItemsNumber, getCards().size());
    }

}
