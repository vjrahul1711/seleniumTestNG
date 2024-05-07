package pageObjectsModel;

import DemoQACommonFiles.POMBaseTestClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrder extends POMBaseTestClass {



    public PlaceOrder(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "[placeholder='Select Country']")
    private WebElement countryInput;
    @FindBy(css = ".ta-results")
    private WebElement resultBox;
    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    private WebElement selectCountry;
    @FindBy(css = ".action__submit")
    private WebElement submit;

    public void enterCountry(String countryName){
        Actions actions = new Actions(driver);
        actions.sendKeys(countryInput,countryName).build().perform();
        waitForWebelementToAppear(resultBox);
        selectCountry.click();
    }
    public void submitOrder(){
        jsExecutorClick(submit,driver);
    }


}
