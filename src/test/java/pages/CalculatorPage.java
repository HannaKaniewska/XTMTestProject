package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.AccountType;
import utils.Currency;
import utils.SeleniumHelper;

public class CalculatorPage {


    @FindBy (className = "pricing-iframe")
    private WebElement pricingFrame;

    @FindBy (name = "accountType")
    private WebElement accountTypeSelect;

    @FindBy (name = "numberOfUsers")
    private WebElement numberOfUsersSelect;

    @FindBy (name = "subscriptionLength")
    private WebElement subscriptionLengthSelect;

    @FindBy (xpath = "//div[@class='total-cost']//span")
    private WebElement totalCostSpan;

    private final WebDriver driver;
    private final SeleniumHelper helper;
    private final Actions action;

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        helper = new SeleniumHelper(driver);
        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public CalculatorPage changeAccountType(AccountType accountType) {
        /*
        If there was any other test action outside of this iFrame, I would've used some change-context
        mechanism, where you can switch to iFrame or default content whenever needed.
         */
        //switch to pricing calculator iFrame
        driver.switchTo().frame(pricingFrame);
        //change Account type
        Select select = new Select(accountTypeSelect);
        select.selectByValue(accountType.toString());
        return this;
    }

    public CalculatorPage changeNumberOfUsers(Integer numberOfUsers) {
        Select select = new Select(numberOfUsersSelect);
        select.selectByValue(numberOfUsers.toString());
        return this;
    }

    public CalculatorPage changeSubscriptionLength(Integer subscriptionLength) {
        Select select = new Select(subscriptionLengthSelect);
        select.selectByValue(subscriptionLength.toString());
        return this;
    }

    public void changeCurrency(Currency currency) {
        WebElement radio = driver.findElement(By.id(currency.toString()));
        action.click(radio).perform();
    }

    public String getTotalCost() {
        helper.waitForElementToBeDisplayed(totalCostSpan);
        return totalCostSpan.getText().replaceAll("\\s","");
    }

}
