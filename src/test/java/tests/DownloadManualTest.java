package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utils.SeleniumHelper;

public class DownloadManualTest extends BaseTest {

    @Test
    public void downloadManualTest() {

        //Get number of XTM Manual files already downloaded in downloadPath
        //I assume that the user has not changed the default download path in his Chrome browser
        String downloadPath = System.getProperty("user.home") + "/Downloads";
        SeleniumHelper helper = new SeleniumHelper(driver);
        String fileNamePrefix = "xtm-manual.x";
        String fileExtension = ".pdf";

        //count the number of files in downloadPath before downloading XTM Manual
        Integer fileCountBefore = helper
                .filteredFileCount(downloadPath, fileNamePrefix, fileExtension);

        //Go to Knowledge base page, then to Documentation page and download XTM Manual document
        new MainPage(driver)
                .chooseAcademyMenuItem()
                .chooseKnowledgeBaseMenuItem()
                .chooseDocumentationMenuItem()
                .downloadXTMManual();

        //Check if the document is downloaded
        Assert.assertTrue(helper.isFileDownloaded(fileCountBefore, downloadPath,
                fileNamePrefix, fileExtension));

    }

}
