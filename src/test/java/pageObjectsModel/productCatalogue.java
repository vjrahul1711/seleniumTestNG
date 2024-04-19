package pageObjectsModel;

import DemoQACommonFiles.POMBaseTestClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class productCatalogue extends POMBaseTestClass {

    public productCatalogue(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".card-body")
    List<WebElement> products;
    @FindBy(css = "#toast-container")
    public WebElement toastMessage;
    @FindBy(css = "[routerlink*='cart']")
    WebElement cartele;
    @FindBy(css = "card")
    public WebElement prodcard;
    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector(".card-body");
    By addToCartBy = By.cssSelector(".card-body button:nth-of-type(2)");

    public List<WebElement> getProductElementList() {
        //waitForWebelementToAppear(prodcard);
        return products;
    }
    public WebElement getProduct(String desiredProduct) {
        WebElement prodName = getProductElementList().stream().filter(s ->
                s.findElement(By.tagName("b")).getText().
                        equals(desiredProduct)).findFirst().orElse(null);
        return prodName;
    }
    public void addToCart(String desiredProduct) {
        waitForWebelementToAppear(toastMessage);
        WebElement product = getProduct(desiredProduct);
        product.findElement(addToCartBy).click();
        waitForWebelementToAppear(toastMessage);
    }

    public void goToCart() {
        waitForWebelementToBeinvisible(spinner);
        cartele.click();
    }


}
