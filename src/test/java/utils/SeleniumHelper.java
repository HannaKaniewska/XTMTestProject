package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.FilenameFilter;
import java.time.Duration;
import java.util.Locale;

/*
To do later:
I could use a better logging instead of System.out.println (e.g. log4j)
 */

public class SeleniumHelper {

    private final WebDriver driver;

    public SeleniumHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToBeDisplayed(WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.pollingEvery(Duration.ofMillis(1000))
                .withTimeout(Duration.ofSeconds(7))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /*
    The function returns number of files in downloadPath.
    The file name must start with fileNamePrefix and end with fileExtension.
     */
    public Integer filteredFileCount(String downloadPath, String fileNamePrefix, String fileExtension) {
        File downloadDirectory = new File(downloadPath);
        //Set filename filter, where the name start with fileNamePrefix and ends with fileExtension
        FilenameFilter filter = (dir, name) ->
                (name.toLowerCase(Locale.ROOT).startsWith(fileNamePrefix.toLowerCase(Locale.ROOT))
                && name.toLowerCase(Locale.ROOT).endsWith(fileExtension.toLowerCase(Locale.ROOT)));
        //get the list of all files matching the filter condition
        File[] fileList = downloadDirectory.listFiles(filter);
        return fileList.length;
    }

    /*
    The function checks if the file with given fileNamePrefix and fileExtension is downloaded into downloadPath.
    It checks every 2 seconds during 30 seconds, if the file is not found, it returns false.
    The checking is done by counting files which match the pattern and comparing it to the number of files
    before downloading. The number should increase by 1.
    (I assume that no other files matching this pattern would appear in downloadPath during running this test)
     */
    public Boolean isFileDownloaded(Integer fileCountBefore, String downloadPath, String fileNamePrefix, String fileExtension) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.pollingEvery(Duration.ofSeconds(2))
                .withTimeout(Duration.ofSeconds(30));
        try {
            wait.until((ExpectedCondition<Boolean>) driver ->
                    (filteredFileCount(downloadPath, fileNamePrefix, fileExtension) == fileCountBefore + 1));
            System.out.println("File is downloaded. Number of files before: " + fileCountBefore
                    + ", file pattern: " + downloadPath + "/" + fileNamePrefix + "*" + fileExtension);
            return true;
        } catch (TimeoutException exception) {
            System.out.println("File is not downloaded. Number of files before: " + fileCountBefore
                    + ", file pattern: " + downloadPath + "/" + fileNamePrefix + "*" + fileExtension);
            return false;
        }

    }
}
