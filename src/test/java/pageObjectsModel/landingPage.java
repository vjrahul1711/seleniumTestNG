package pageObjectsModel;

import DemoQACommonFiles.POMBaseTestClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class landingPage extends POMBaseTestClass {


    public landingPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "userEmail")
    private WebElement userEmail;
    @FindBy(id ="userPassword")
    private WebElement userPassword;
    @FindBy(id ="login")
    private WebElement submit;
    @FindBy(css="[class*='flyInOut']")
    private WebElement errorMessage;



    public void loginapp(String email, String pass){
        userEmail.sendKeys(email);
        userPassword.sendKeys(pass);
        submit.click();
        //productCatalogue prodcatalogue = new productCatalogue(getDriver());
        //return  prodcatalogue;
    }
    public String getErrorMSg(WebDriver driver) {
        waitForWebelementToAppear(errorMessage);
        String errMsg= errorMessage.getText();
        System.out.println(errMsg);
        return errMsg;
    }


}

