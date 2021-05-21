package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumHelper;

public class KnowledgeBasePage {

    @FindBy (id = "menu-item-5037")
    private WebElement documentationMenuItem;


    private final WebDriver driver;
    private final SeleniumHelper helper;

    public KnowledgeBasePage (WebDriver driver) {
        this.driver = driver;
        this.helper = new SeleniumHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public DocumentationPage chooseDocumentationMenuItem() {
        helper.waitForElementToBeDisplayed(documentationMenuItem);
        documentationMenuItem.click();
        return new DocumentationPage(driver);
    }

}
