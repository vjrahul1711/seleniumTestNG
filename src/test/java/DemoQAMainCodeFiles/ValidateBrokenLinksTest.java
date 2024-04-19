package DemoQAMainCodeFiles;

import DemoQACommonFiles.BaseTestClass1;
import DemoQACommonFiles.POMBaseTestClass;
import DemoQACommonFiles.configuration;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ValidateBrokenLinksTest extends BaseTestClass1 {


    @Test
    public void brokenLinkValidate() throws IOException {
        BrowserLaunch(configuration.TEST_URL_Automation_Practice);
        SoftAssert as = new SoftAssert();
        List<WebElement> allLinks = getDriver().findElements(By.cssSelector("li[class='gf-li'] a"));
        for (int i=0; i<allLinks.size();i++){
            String url = allLinks.get(i).getAttribute("href");
            HttpURLConnection conn= (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            int statusCode= conn.getResponseCode();
            System.out.println(statusCode);
            as.assertTrue(statusCode<400,"Broken link is present with name "+allLinks
                    .get(i).getText() +" and Status Code "+statusCode);

        }
        as.assertAll();

        getDriver().quit();

    }




}
