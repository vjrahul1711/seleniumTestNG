package pageObjectsModel;

import DemoQACommonFiles.POMBaseTestClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class confirmationPage extends POMBaseTestClass {
    public confirmationPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".hero-primary")
    WebElement ThankyouNote;
    public String getThankyouMsg(){
        waitForWebelementToAppear(ThankyouNote);
        String confirmationMsg= ThankyouNote.getText();
        return confirmationMsg;
    }
}
