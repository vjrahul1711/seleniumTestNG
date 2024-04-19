import DemoQACommonFiles.POMBaseTestClass;
import DemoQACommonFiles.Form1data;
import DemoQACommonFiles.Retry;
import DemoQACommonFiles.configuration;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjectsModel.landingPage;

public class POMErrorValidationTest extends POMBaseTestClass {


    @Test(groups = {"POM"},retryAnalyzer = Retry.class)
    public void validateLogin() throws InterruptedException {
        landingPage landingpage = new landingPage(getDriver());
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        landingpage.loginapp(Form1data.FORM_Email_Id, configuration.TEST_PWD_DEMOQA_Invalid);
        Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMSg(getDriver()));
    }

}
