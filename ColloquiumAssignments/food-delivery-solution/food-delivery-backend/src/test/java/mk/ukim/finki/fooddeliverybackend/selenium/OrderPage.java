package mk.ukim.finki.fooddeliverybackend.selenium;

import lombok.Getter;
import mk.ukim.finki.fooddeliverybackend.model.domain.Dish;
import mk.ukim.finki.fooddeliverybackend.model.domain.Order;
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
public class OrderPage extends AbstractPage {

    private static final String ORDER_ITEM_SELECTOR = ".order-list";
    private static final String CONFIRM_BUTTON_SELECTOR = ".confirm-btn";
    private static final String CANCEL_BUTTON_SELECTOR = ".cancel-btn";

    @FindBy(css = ORDER_ITEM_SELECTOR)
    private WebElement orderList;

    @FindBy(css = CONFIRM_BUTTON_SELECTOR)
    private WebElement confirmButton;

    @FindBy(css = CANCEL_BUTTON_SELECTOR)
    private WebElement cancelButton;

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public static OrderPage to(WebDriver driver) {
        get(driver, "/orders");
        assertRelativeUrl(driver, "/orders");

        OrderPage orderPage = PageFactory.initElements(driver, OrderPage.class);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(OrderPage::isPageFullyLoaded);

        return orderPage;
    }

    public static boolean isPageFullyLoaded(WebDriver driver) {
        return AbstractPage.areElementsPresentAndVisible(
                driver,
                ORDER_ITEM_SELECTOR,
                CONFIRM_BUTTON_SELECTOR,
                CANCEL_BUTTON_SELECTOR
        );
    }

}
