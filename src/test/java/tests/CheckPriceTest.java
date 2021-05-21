package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CalculatorPage;
import pages.MainPage;
import utils.AccountType;
import utils.Currency;

public class CheckPriceTest extends BaseTest {

    @Test
    public void checkPriceTest() {

        //Go to Calculator page, change account type, number of users and subscription length
        CalculatorPage calculatorPage = new MainPage(driver)
                .choosePricingMenuItem()
                .chooseCalculatorMenuItem()
                .changeAccountType(AccountType.LSP)
                .changeNumberOfUsers(7)
                .changeSubscriptionLength(3);

        //check the price in GBP (default selection), EUR and USD
        Assert.assertEquals(calculatorPage.getTotalCost(), "£1065,00");
        calculatorPage.changeCurrency(Currency.EUR);
        Assert.assertEquals(calculatorPage.getTotalCost(), "€1214,10");
        calculatorPage.changeCurrency(Currency.USD);
        Assert.assertEquals(calculatorPage.getTotalCost(), "$1533,60");
    }

}
