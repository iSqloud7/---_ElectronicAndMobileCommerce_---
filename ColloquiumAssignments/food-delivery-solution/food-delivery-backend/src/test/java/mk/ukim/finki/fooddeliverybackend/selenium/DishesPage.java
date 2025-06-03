package mk.ukim.finki.fooddeliverybackend.selenium;

import lombok.Getter;
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
public class DishesPage extends AbstractPage {

    private static final String CARD_SELECTOR = ".card";
    private static final String ADD_BUTTON_SELECTOR = ".add-item";
    private static final String EDIT_BUTTON_SELECTOR = ".edit-item";
    private static final String DELETE_BUTTON_SELECTOR = ".delete-item";

    @FindBy(css = CARD_SELECTOR)
    private List<WebElement> cards;

    @FindBy(css = ADD_BUTTON_SELECTOR)
    private List<WebElement> addButton;

    @FindBy(css = EDIT_BUTTON_SELECTOR)
    private List<WebElement> editButtons;

    @FindBy(css = DELETE_BUTTON_SELECTOR)
    private List<WebElement> deleteButtons;

    public DishesPage(WebDriver driver) {
        super(driver);
    }

    public static DishesPage to(WebDriver driver) {
        get(driver, "/dishes");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(DishesPage::isPageFullyLoaded);

        return PageFactory.initElements(driver, DishesPage.class);
    }

    public static boolean isPageFullyLoaded(WebDriver driver) {
        return AbstractPage.areElementsPresentAndVisible(
                driver,
                CARD_SELECTOR,
                ADD_BUTTON_SELECTOR,
                EDIT_BUTTON_SELECTOR,
                DELETE_BUTTON_SELECTOR
        );
    }

    public void assertButtons(int deleteButtonsCount, int editButtonsCount, int addButtonsCount) {
        ExamAssert.assertEquals("delete btn count", deleteButtonsCount, this.getDeleteButtons().size());
        ExamAssert.assertEquals("edit btn count", editButtonsCount, this.getEditButtons().size());
        ExamAssert.assertEquals("add btn count", addButtonsCount, this.getAddButton().size());
    }

    public boolean assertItems(int expectedItemsNumber) {
        return ExamAssert.assertEquals("Number of items", expectedItemsNumber, getCards().size());
    }

}
