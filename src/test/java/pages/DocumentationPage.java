package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentationPage {

    @FindBy (linkText = "XTM Manual")
    private WebElement manualLink;

    @FindBy (className = "academy-main-header-title")
    private WebElement documentationHeader;

    private final WebDriver driver;

    public DocumentationPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void downloadXTMManual() {
        //scroll to Documentation header
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", documentationHeader);
        //click on XTM Manual link
        manualLink.click();
    }

}
