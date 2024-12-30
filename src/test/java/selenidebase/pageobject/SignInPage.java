package selenidebase.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SignInPage {

    private final SelenideElement emailField = $("[name='email']");

    public boolean emailFieldIsPresent() {
        emailField.shouldBe(Condition.visible);
        return emailField.isDisplayed();
    }
}