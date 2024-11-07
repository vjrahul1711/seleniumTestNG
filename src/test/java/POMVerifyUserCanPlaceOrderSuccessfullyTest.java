import DemoQACommonFiles.*;
import PassionGaming.utils.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjectsModel.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class POMVerifyUserCanPlaceOrderSuccessfullyTest extends BaseTestClass {


    @Test(groups = {"POM"},retryAnalyzer = Retry.class)
    //@Test(groups ={"POM"})
    public void TestSubmitOrder() throws IOException, URISyntaxException {

        BrowserLaunch("https://rahulshettyacademy.com/client");

        //LOGIN INTO APP
        landingPage landingpage = new landingPage(getDriver());
        landingpage.loginapp(Form1data.FORM_Email_Id, configuration.TEST_PWD_DEMOQA);

        //PRODUCT TO BE ADDED IN CART AND BUYOUT
        String desiredProduct = "ADIDAS ORIGINAL";
        String countryName = "india";

        //PRODUCT CATALOGUE PAGE ACTIONS
        productCatalogue prodcatalogue = new productCatalogue(getDriver());
        //NeedClarificationOn this topic
        //waitForWebelementToAppear(prodcatalogue.toastMessage);
        //waitForWebelementToAppear(prodcatalogue.prodcard);
        prodcatalogue.addToCart(desiredProduct);
        prodcatalogue.goToCart();

        //CART PAGE ACTIONS
        cartPage cartPage = new cartPage(getDriver());
        cartPage.verifyCartItem(desiredProduct);
        cartPage.clickonbuy();

        //PLACE ORDER PAGE ACTIONS
        PlaceOrder placeorder = new PlaceOrder(getDriver());
        placeorder.enterCountry(countryName);
        placeorder.submitOrder();

        //ORDER CONFIRMATION PAGE ACTIONS
        confirmationPage confirmationpage =new confirmationPage(getDriver());
        String ExpectedConfMsg= confirmationpage.getThankyouMsg();
        Assert.assertTrue(ExpectedConfMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        //getDriver().close();
    }

}
