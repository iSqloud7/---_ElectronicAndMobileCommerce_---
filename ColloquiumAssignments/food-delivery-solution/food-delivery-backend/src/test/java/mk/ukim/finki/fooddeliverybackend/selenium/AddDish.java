package mk.ukim.finki.fooddeliverybackend.selenium;

import lombok.Getter;
import mk.ukim.finki.fooddeliverybackend.dto.domain.CreateDishDto;
import mk.ukim.finki.fooddeliverybackend.util.ExamAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class AddDish extends AbstractPage {

    private static final String ADD_BUTTON_SELECTOR = ".add-item";
    private static final String CARD_SELECTOR = ".card";
    private static final String NAME_FIELD_SELECTOR = "input[name='name']";
    private static final String DESCRIPTION_FIELD_SELECTOR = "textarea[name='description']";
    private static final String PRICE_FIELD_SELECTOR = "input[name='price']";
    private static final String QUANTITY_FIELD_SELECTOR = "input[name='quantity']";
    private static final String RESTAURANT_FIELD_SELECTOR = ".restaurant-select";
    private static final String SUBMIT_BUTTON_SELECTOR = ".submit-btn";

    @FindBy(css = ADD_BUTTON_SELECTOR)
    private WebElement addButton;

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

    public AddDish(WebDriver driver) {
        super(driver);
    }

    public static AddDish add(WebDriver driver, CreateDishDto createDishDto) {
        get(driver, "/dishes");
        assertRelativeUrl(driver, "/dishes");

        AddDish addDish = PageFactory.initElements(driver, AddDish.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(AddDish::isPageFullyLoaded);

        addDish.addButton.click();

        addDish = PageFactory.initElements(driver, AddDish.class);
        wait.until(AddDish::isFormFullyLoaded);

        addDish.nameField.sendKeys(createDishDto.name());
        addDish.descriptionField.sendKeys(createDishDto.description());
        addDish.priceField.sendKeys(String.valueOf(createDishDto.price()));
        addDish.quantityField.sendKeys(String.valueOf(createDishDto.quantity()));
        addDish.restaurantField.click();

        String restaurantOptionSelector = String.format("li.restaurant-option[data-value='%s']", createDishDto.restaurantId());
        wait.until(webDriver -> !webDriver.findElements(By.cssSelector(restaurantOptionSelector)).isEmpty());
        driver.findElements(By.cssSelector(restaurantOptionSelector)).getFirst().click();
        addDish.submitButton.click();

        addDish = PageFactory.initElements(driver, AddDish.class);
        wait.until(d -> !isFormFullyLoaded(driver));

        return addDish;
    }

    private static boolean isPageFullyLoaded(WebDriver driver) {
        return AbstractPage.areElementsPresentAndVisible(
                driver,
                ADD_BUTTON_SELECTOR,
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
