package mk.ukim.finki.fooddeliverybackend.selenium;

import lombok.Getter;
import mk.ukim.finki.fooddeliverybackend.dto.domain.CreateDishDto;
import mk.ukim.finki.fooddeliverybackend.dto.domain.DisplayDishDto;
import mk.ukim.finki.fooddeliverybackend.util.ExamAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class EditDish extends AbstractPage {

    private static final String CARD_SELECTOR = ".card";
    private static final String NAME_FIELD_SELECTOR = "input[name='name']";
    private static final String DESCRIPTION_FIELD_SELECTOR = "textarea[name='description']";
    private static final String PRICE_FIELD_SELECTOR = "input[name='price']";
    private static final String QUANTITY_FIELD_SELECTOR = "input[name='quantity']";
    private static final String RESTAURANT_FIELD_SELECTOR = ".restaurant-select";
    private static final String SUBMIT_BUTTON_SELECTOR = ".submit-btn";

    @FindBy(css = CARD_SELECTOR)
    private List<WebElement> cards;

    @FindBy(css = NAME_FIELD_SELECTOR)
    private WebElement nameField;

    @FindBy(css = DESCRIPTION_FIELD_SELECTOR)
    private WebElement descriptionField;

    @FindBy(css = PRICE_FIELD_SELECTOR)
    private WebElement priceField;

    @FindBy(css = QUANTITY_FIELD_SELECTOR)
    private WebElement quantityField;

    @FindBy(css = RESTAURANT_FIELD_SELECTOR)
    private WebElement restaurantField;

    @FindBy(css = SUBMIT_BUTTON_SELECTOR)
    private WebElement submitButton;

    public EditDish(WebDriver driver) {
        super(driver);
    }

    public static EditDish edit(WebDriver driver, Long dishId, CreateDishDto createDishDto) {
        get(driver, "/dishes");
        assertRelativeUrl(driver, "/dishes");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(EditDish::isPageFullyLoaded);

        String editButtonSelector = String.format(".card[data-id='%s'] .edit-item", dishId);
        WebElement editButton = driver.findElement(By.cssSelector(editButtonSelector));

        editButton.click();

        EditDish editDish = PageFactory.initElements(driver, EditDish.class);
        wait.until(EditDish::isFormFullyLoaded);

        editDish.nameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        editDish.nameField.sendKeys(createDishDto.name());

        editDish.descriptionField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        editDish.descriptionField.sendKeys(createDishDto.description());

        editDish.priceField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        editDish.priceField.sendKeys(String.valueOf(createDishDto.price()));

        editDish.quantityField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        editDish.quantityField.sendKeys(String.valueOf(createDishDto.quantity()));

        editDish.restaurantField.click();
        String restaurantOptionSelector = String.format("li.restaurant-option[data-value='%s']", createDishDto.restaurantId());
        wait.until(webDriver -> !webDriver.findElements(By.cssSelector(restaurantOptionSelector)).isEmpty());
        driver.findElements(By.cssSelector(restaurantOptionSelector)).getFirst().click();

        editDish.submitButton.click();

        editDish = PageFactory.initElements(driver, EditDish.class);
        wait.until(d -> !isFormFullyLoaded(d) && isPageFullyLoaded(d));

        return editDish;
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
                NAME_FIELD_SELECTOR,
                DESCRIPTION_FIELD_SELECTOR,
                PRICE_FIELD_SELECTOR,
                QUANTITY_FIELD_SELECTOR,
                RESTAURANT_FIELD_SELECTOR,
                SUBMIT_BUTTON_SELECTOR
        );
    }

    public void assertItems(int expectedItemsNumber) {
        ExamAssert.assertEquals("Number of items", expectedItemsNumber, getCards().size());
    }

}
