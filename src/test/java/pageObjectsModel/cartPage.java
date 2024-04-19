package pageObjectsModel;

import DemoQACommonFiles.POMBaseTestClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class cartPage extends POMBaseTestClass {

    public cartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".infoWrap button:nth-of-type(1)")
    WebElement buyButton;

    @FindBy(css = "[class='cartSection'] h3")
    List<WebElement> cartItems;

    public List<WebElement> getCartItems() {
        waitForWebelementToAppear(buyButton);
        return cartItems;
    }

    public WebElement verifyCartItem(String desiredProduct) {
        WebElement item = getCartItems().stream().filter(s -> s.getText().
                equalsIgnoreCase(desiredProduct)).findFirst().orElse(null);
        return item;
    }

    public void clickonbuy() {
        buyButton.click();
    }


}
