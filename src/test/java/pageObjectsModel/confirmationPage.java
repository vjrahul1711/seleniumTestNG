package PassionGaming.pages;

import DemoQACommonFiles.BaseTestClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class confirmationPage extends BaseTestClass {
    public confirmationPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".hero-primary")
    private WebElement ThankyouNote;
    public String getThankyouMsg(){
        waitForWebelementToAppear(ThankyouNote);
        String confirmationMsg= ThankyouNote.getText();
        return confirmationMsg;
    }
}
