import DemoQACommonFiles.BaseTestClass1;
import DemoQACommonFiles.configuration;
import DemoQACommonFiles.excelToDataObjectUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;


public class LoginLogoutDataProviderExcelTest extends BaseTestClass1 {



    @Test(dataProvider ="testData")
    // With valid User and valid password
    public void testLoginValidCred(String username, String password) throws IOException {
        //Method for launching the browser
        BrowserLaunch(configuration.TEST_URL_DEMOQA);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement Elem1 = getDriver().findElement(By.xpath("//h5[text()=\"Book Store Application\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", Elem1);
        executor.executeScript("arguments[0].click();", getDriver().findElement(By.xpath("//span[text()=\"Login\"]")));

        //Login
        getDriver().findElement(By.xpath("//input[@id=\"userName\"]")).sendKeys(username);
        getDriver().findElement(By.xpath("//input[@id=\"password\"]")).sendKeys(password);
        executor.executeScript("arguments[0].click();", getDriver().findElement(By.xpath("//button[@id=\"login\"]")));


        //log out and invalid scenario

        boolean g = !getDriver().findElements(By.xpath("//label[@id=\"userName-value\"]")).isEmpty();
        if (g) {
            String actualUname = getDriver().findElement(By.xpath("//label[@id=\"userName-value\"]")).getText();
            Assert.assertEquals(configuration.TEST_UNAME_DEMOQA, actualUname, "Verified user name");
            executor.executeScript("arguments[0].click()", getDriver().findElement(By.xpath("//button[@id=\"submit\"]")));
            System.out.println("Login completed");
        }
        else {
            String invalidMsg = getDriver().findElement(By.id("name")).getText();
            Assert.assertEquals(invalidMsg, configuration.TEST_MSG_DEMOQA_Invalid, "invalid cred");
            System.out.println("Login not completed");
        }
        getDriver().quit();
    }

    @DataProvider
    public Object[][] testData() throws IOException {
    excelToDataObjectUtility dataGet = new excelToDataObjectUtility();
        return dataGet.excelData();
    }

}
