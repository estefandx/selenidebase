package selenidebase.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PreviewCheckoutPage {


    private final SelenideElement goToCart = $("#nav-cart-count");


    public void goToCart() {
        goToCart.click();
    }
}
