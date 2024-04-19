package DemoQAMainCodeFiles;

import DemoQACommonFiles.BaseTestClass1;
import DemoQACommonFiles.configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;


public class LoginLogoutHashMapDataProviderTest extends BaseTestClass1 {

    @Test(dataProvider = "getData")
    // With valid User and valid password
    public void testLoginValidCred(HashMap<String,String>input) throws IOException {
        //Method for launching the browser
        BrowserLaunch(configuration.TEST_URL_DEMOQA);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement Elem1 = getDriver().findElement(By.xpath("//h5[text()=\"Book Store Application\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", Elem1);
        executor.executeScript("arguments[0].click();", getDriver().findElement(By.xpath("//span[text()=\"Login\"]")));

        //Login
        getDriver().findElement(By.xpath("//input[@id=\"userName\"]")).sendKeys(input.get("username"));
        getDriver().findElement(By.xpath("//input[@id=\"password\"]")).sendKeys(input.get("password"));
        executor.executeScript("arguments[0].click();", getDriver().findElement(By.xpath("//button[@id=\"login\"]")));


        //log out and invalid scenario

        boolean g = !getDriver().findElements(By.xpath("//label[@id=\"userName-value\"]")).isEmpty();
        if (g) {
            String actualUname = getDriver().findElement(By.xpath("//label[@id=\"userName-value\"]")).getText();
            Assert.assertEquals(configuration.TEST_UNAME_DEMOQA, actualUname, "Verified user name");
            executor.executeScript("arguments[0].click()", getDriver().findElement(By.xpath("//button[@id=\"submit\"]")));
        }
        else {
            String invalidMsg = getDriver().findElement(By.id("name")).getText();
            Assert.assertEquals(invalidMsg, configuration.TEST_MSG_DEMOQA_Invalid, "invalid cred");
        }
        getDriver().quit();
    }

//    @DataProvider
//    public Object[][] getData() {
//        Object[][] data = new Object[4][2];
//        //Valid User and Password
//        data[0][0] = configuration.TEST_UNAME_DEMOQA;
//        data[0][1] = configuration.TEST_PWD_DEMOQA;
//
//
//        data[1][0] = configuration.TEST_UNAME_DEMOQA;
//        data[1][1] = "passwordWrong";
//
//        data[2][0] = "usernameWrong";
//        data[2][1] = configuration.TEST_PWD_DEMOQA_Invalid;
//
//        data[3][0] = "usernameWrong";
//        data[3][1] = configuration.TEST_PWD_DEMOQA;

//        Object[][] data = {{configuration.TEST_UNAME_DEMOQA,configuration.TEST_PWD_DEMOQA},
//                {configuration.TEST_UNAME_DEMOQA,"passwordWrong"},
//                {"usernameWrong",configuration.TEST_PWD_DEMOQA_Invalid,},
//                {"usernameWrong",configuration.TEST_PWD_DEMOQA}};
//       return data;
//    }
//}

    @DataProvider
    public Object[][] getData() {

        HashMap<String,String> map1 = new HashMap<String,String>();
        map1.put("username",configuration.TEST_UNAME_DEMOQA);
        map1.put("password",configuration.TEST_PWD_DEMOQA);

        HashMap<String,String> map2 = new HashMap<String,String>();
        map2.put("username",configuration.TEST_UNAME_DEMOQA);
        map2.put("password","passwordWrong");

        HashMap<String,String> map3 = new HashMap<String,String>();
        map3.put("username","usernameWrong");
        map3.put("password",configuration.TEST_PWD_DEMOQA_Invalid);

        HashMap<String,String> map4 = new HashMap<String,String>();
        map4.put("username","usernameWrong");
        map4.put("password",configuration.TEST_PWD_DEMOQA);

        return new Object[][]  {{map1},{map2},{map3},{map4}};
    }
}



