package selenidebase.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ShoppingCartPage {

    private final ElementsCollection productCartList = $$(".a-truncate-cut");
    private final SelenideElement subTotalItems = $("#sc-subtotal-label-activecart");
    private final SelenideElement proceedCheckout = $("[name='proceedToRetailCheckout']");
    private  By actionLinksItems = By.xpath("//span[@data-action='quantity']");

    public int itemsCartList() {
        return productCartList.size();
    }

    public int getItemCount() throws InterruptedException {
        Thread.sleep(1000);
        String text = subTotalItems.getText();
        Pattern pattern = Pattern.compile("\\((\\d+) items\\)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            return 0;
        }
    }

    public void deleteItem(int positionItem) {
        $$x(".//input[@value='Delete']").get(positionItem).click();
    }

    public void addNumberItems(int numberItems, int positionItem) {
        for (int i = 1; i < numberItems; i++) {
            try {

                SelenideElement item = $$(actionLinksItems).get(positionItem)
                        .$x(".//button[@data-a-selector='increment']");
                item.click();
                Thread.sleep(1000);
            } catch (StaleElementReferenceException e) {
                System.out.println("Obsolete item found, retrying... Exception: " + e.getMessage());
                i--;
                sleep(500);
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                break;
            }
        }
    }

    public void proceedCheckout() {
        proceedCheckout.click();
    }
}
