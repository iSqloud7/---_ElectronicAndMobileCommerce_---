package mk.ukim.finki.fooddeliverybackend.selenium;

import lombok.Getter;
import mk.ukim.finki.fooddeliverybackend.model.domain.Dish;
import mk.ukim.finki.fooddeliverybackend.util.ExamAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class DishPage extends AbstractPage {

    private static final String NAME_SELECTOR = ".dish-name";
    private static final String DESCRIPTION_SELECTOR = ".dish-desc";
    private static final String PRICE_SELECTOR = ".dish-price";
    private static final String QUANTITY_SELECTOR = ".dish-quantity";
    private static final String RESTAURANT_NAME_SELECTOR = ".restaurant-name";
    private static final String ORDER_BUTTON_SELECTOR = ".add-to-order";

    @FindBy(css = NAME_SELECTOR)
    private WebElement name;

    @FindBy(css = DESCRIPTION_SELECTOR)
    private WebElement description;

    @FindBy(css = PRICE_SELECTOR)
    private WebElement price;

    @FindBy(css = QUANTITY_SELECTOR)
    private WebElement quantity;

    @FindBy(css = RESTAURANT_NAME_SELECTOR)
    private WebElement restaurantName;

    @FindBy(css = ORDER_BUTTON_SELECTOR)
    private WebElement orderButton;

    public DishPage(WebDriver driver) {
        super(driver);
    }

    public static DishPage order(WebDriver driver, Long dishId) {
        get(driver, String.format("/dishes/%s", dishId));
        assertRelativeUrl(driver, String.format("/dishes/%s", dishId));

        DishPage dishPage = PageFactory.initElements(driver, DishPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(DishPage::isPageFullyLoaded);

        dishPage.orderButton.click();

        return dishPage;
    }

    public static DishPage display(WebDriver driver, Dish dish) {
        get(driver, String.format("/dishes/%s", dish.getId()));
        assertRelativeUrl(driver, String.format("/dishes/%s", dish.getId()));

        DishPage dishPage = PageFactory.initElements(driver, DishPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(DishPage::isPageFullyLoaded);

        ExamAssert.assertEquals("The name differs.", dish.getName(), dishPage.name.getText().trim());
        ExamAssert.assertEquals("The description differs.", dish.getDescription(), dishPage.description.getText().trim());
        ExamAssert.assertEquals("The price differs.", String.format("$%s", dish.getPrice()), dishPage.price.getText().trim());
        ExamAssert.assertEquals("The quantity differs.", String.format("%s serving(s) available", dish.getQuantity()), dishPage.quantity.getText().trim());
        ExamAssert.assertEquals("The restaurant name differs.", dish.getRestaurant().getName(), dishPage.restaurantName.getText().trim());

        return dishPage;
    }

    private static boolean isPageFullyLoaded(WebDriver driver) {
        return AbstractPage.areElementsPresentAndVisible(
                driver,
                NAME_SELECTOR,
                DESCRIPTION_SELECTOR,
                PRICE_SELECTOR,
                QUANTITY_SELECTOR,
                RESTAURANT_NAME_SELECTOR,
                ORDER_BUTTON_SELECTOR
        );
    }

}
