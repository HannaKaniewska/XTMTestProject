package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumHelper;

public class MainPage {

    /*
    Do the menu item IDs change frequently? If so, I need to use another locator
     */
    @FindBy(id = "menu-item-35")
    private WebElement pricingMenuItem;

    @FindBy(id = "menu-item-7220")
    private WebElement calculatorMenuItem;

    @FindBy (id = "menu-item-88")
    private WebElement academyMenuItem;

    @FindBy (id = "menu-item-91")
    private WebElement knowledgeBaseMenuItem;

    private final WebDriver driver;
    private final SeleniumHelper helper;
    private final Actions action;

    public MainPage (WebDriver driver) {
        this.driver = driver;
        this.helper = new SeleniumHelper(driver);
        this.action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage choosePricingMenuItem() {
        helper.waitForElementToBeDisplayed(pricingMenuItem);
        //hover the mouse cursor over Pricing menu item
        action.moveToElement(pricingMenuItem).perform();
        return this;
    }

    public CalculatorPage chooseCalculatorMenuItem() {
        helper.waitForElementToBeDisplayed(calculatorMenuItem);
        calculatorMenuItem.click();
        return new CalculatorPage(driver);
    }

    public MainPage chooseAcademyMenuItem() {
        helper.waitForElementToBeDisplayed(academyMenuItem);
        //hover the mouse cursor over Academy menu item
        action.moveToElement(academyMenuItem).perform();
        return this;
    }

    public KnowledgeBasePage chooseKnowledgeBaseMenuItem() {
        helper.waitForElementToBeDisplayed(knowledgeBaseMenuItem);
        knowledgeBaseMenuItem.click();
        return new KnowledgeBasePage(driver);
    }

}
