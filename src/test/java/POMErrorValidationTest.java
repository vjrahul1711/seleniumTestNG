import DemoQACommonFiles.*;
import PassionGaming.utils.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjectsModel.landingPage;

import java.io.IOException;
import java.net.URISyntaxException;

public class POMErrorValidationTest extends BaseTestClass {


    @Test(groups = {"POM"},retryAnalyzer = Retry.class)
    public void validateLogin() throws InterruptedException, IOException, URISyntaxException {
        BrowserLaunch("https://rahulshettyacademy.com/client");
        landingPage landingpage = new landingPage(getDriver());
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        landingpage.loginapp(Form1data.FORM_Email_Id, configuration.TEST_PWD_DEMOQA_Invalid);
        Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMSg(getDriver()));
        //getDriver().quit();
    }

}
