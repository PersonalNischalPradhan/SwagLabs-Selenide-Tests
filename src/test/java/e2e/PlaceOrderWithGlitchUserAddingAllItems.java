package e2e;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.checkerframework.checker.units.qual.K;
import org.testng.annotations.Test;
import pages.*;
import utils.TestUtils;

import static org.testng.Assert.assertEquals;

public class PlaceOrderWithGlitchUserAddingAllItems extends BaseTest {
    KeywordManager keywordManager= new KeywordManager();
    private final String performance_glitch_user = TestUtils.getProperty("performance.glitch.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    private final String expected_OrderConfirmationText = TestUtils.getProperty("expected.OrderConfirmationText");
    private final String expected_OrderDispatchedText = TestUtils.getProperty("expected.OrderDispatchedText");
    private final int expected_ItemCount = Integer.parseInt(TestUtils.getProperty("expected.ItemCount"));
    private final double expected_OrderPrice = Double.parseDouble(TestUtils.getProperty("expected.orderPrice"));
    private final String expected_CardDetails = TestUtils.getProperty("expected.cardDetails");
    private final String expected_shippingInfoDetailsActual = TestUtils.getProperty("expected.shippingDetails");
    private final String first_name = TestUtils.getProperty("first.name");
    private final String last_name = TestUtils.getProperty("last.name");
    private final String valid_address = TestUtils.getProperty("valid.address");
    @Epic("Swag Labs")
    @Description("Place order with all the items present with a glitch user")
    @Test
    public void addProductsToBasketGlitchUser() throws InterruptedException {
        keywordManager.loginPage.login(performance_glitch_user,valid_password);
        Selenide.sleep(4000);
        keywordManager.homePage.addAllItemsToBasket();
        validateBasketCount();
        Selenide.sleep(2000);
        keywordManager.homePage.clickOnBasketIcon();
        keywordManager.yourCartPage.clickOnCheckoutButton();
        keywordManager.checkoutInfoPage.enterFirstName(first_name);
        keywordManager.checkoutInfoPage.enterLastName(last_name);
        keywordManager.checkoutInfoPage.enterPostCode(valid_address);
        keywordManager.checkoutInfoPage.clickOnContinue();
        validateOrderPrice();
        validateCardDetails();
        validateShippingInfoDetails();
        keywordManager.checkoutOverviewPage.clickOnFinishButton();
        validateOrderConfirmationText();
        validateOrderDispatched();

    }

    @Step
    public void validateOrderConfirmationText() {
        String actualUiConfirmationText = keywordManager.orderConfirmationPage.getOrderConfirmationMessage();
        assertEquals(actualUiConfirmationText, expected_OrderConfirmationText,"Order Confirmation text  mismatched ");
    }
    @Step
    public void validateOrderDispatched() {
        String actualUiDispatchedText = keywordManager.orderConfirmationPage.getOrderDispatchedMessage();
        assertEquals(actualUiDispatchedText, expected_OrderDispatchedText,"Order Confirmation text  mismatched ");
    }
    @Step
    public void validateBasketCount() {
        int actualItemCount = keywordManager.homePage.getItemsInBasketCount();
        assertEquals(actualItemCount, expected_ItemCount,"Basket Count Mismatched for the user " +performance_glitch_user);
    }
    @Step
    public void validateOrderPrice() {
        double orderPriceActual = keywordManager.checkoutOverviewPage.getOrderPriceDetailsText();
        assertEquals(orderPriceActual, expected_OrderPrice,"Order Price Mismatched for the user " +performance_glitch_user);
    }
    @Step
    public void validateShippingInfoDetails() {
        String shippingInfoDetailsActual = keywordManager.checkoutOverviewPage.getShippingInfoDetailsText();
        assertEquals(shippingInfoDetailsActual, expected_shippingInfoDetailsActual,"Shipping Info Details Mismatched for the user " +performance_glitch_user);
    }
    @Step
    public void validateCardDetails() {
        String cardDetailsActual = keywordManager.checkoutOverviewPage.getCardDetailsText();
        assertEquals(cardDetailsActual, expected_CardDetails,"Card Details Mismatched for the user " +performance_glitch_user);
    }
}
