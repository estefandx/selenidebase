package selenidebase.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DetailsProductPage {


    private final SelenideElement addProductCart = $("#add-to-cart-button");
    private final SelenideElement productTitle = $("#productTitle");
    private final SelenideElement confirmationProduct = $("#add-to-cart-confirmation-image");


    public void addProductCart() {
        addProductCart.click();
    }


    public String getProductTitle() {
        return productTitle.shouldBe(visible).getText();
    }


    public boolean isVisibleProductConfirmation() {
        return confirmationProduct.is(visible);
    }
}