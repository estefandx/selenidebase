package selenidebase.test;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenidebase.Utilities;
import selenidebase.pageobject.*;

import java.util.Objects;

public class BookDepositoryTest  extends  BaseTest{

    @Test
    public void resultBooksTest(){
        SearchBookPage searchBookPage = new SearchBookPage();
        int lowerBound = 3;

        searchBookPage.searchProduct("Thinking in Java");
        int actualNumber = searchBookPage.getResultProduct();

        Assert.assertTrue(actualNumber >= lowerBound,
                "The number " + actualNumber + " is  less " + lowerBound);

    }

    @Test
    public void applyKindleFilterTest(){
        SearchBookPage searchBookPage = new SearchBookPage();

        searchBookPage.searchProduct("Thinking in Java");
        int actualNumber = searchBookPage.getResultProduct();
        searchBookPage.applyKindleEditionFilter();

        int currentNumberProductType = searchBookPage.getBookWithType("Kindle");

        Assert.assertTrue(Math.abs(actualNumber - currentNumberProductType) <= 5);

    }

    @Test
    public void applyPaperbackFilterTest(){
        SearchBookPage searchBookPage = new SearchBookPage();

        searchBookPage.searchProduct("Thinking in Java");
        searchBookPage.applyPaperBackFilter();
        int actualNumber = searchBookPage.getResultProduct();
        int currentNumberProductType = searchBookPage.getBookWithType("Paperback");

        Assert.assertEquals(actualNumber,currentNumberProductType);

    }

    @Test
    public void checkoutTest() throws InterruptedException {
        SearchBookPage searchBookPage = new SearchBookPage();
        DetailsProductPage detailsProductPage = new DetailsProductPage();
        PreviewCheckoutPage previewCheckoutPage = new PreviewCheckoutPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        SignInPage singInPage = new SignInPage();

        int actualnumberBooks = Utilities.generateRandomNumber(2,5);

        searchBookPage.searchProduct("Sthepen king");
        searchBookPage.applyPaperBackFilter();
        searchBookPage.selectProductByPosition(1);
        detailsProductPage.addProductCart();
        searchBookPage.searchProduct("Sthepen king");
        searchBookPage.applyPaperBackFilter();
        searchBookPage.selectProductByPosition(4);
        detailsProductPage.addProductCart();
        previewCheckoutPage.goToCart();
        int actualItemslist = shoppingCartPage.itemsCartList();
        int actualSubtotalItems = shoppingCartPage.getItemCount();
        Assert.assertEquals(actualItemslist,2);
        Assert.assertEquals(actualSubtotalItems,2);

        shoppingCartPage.addNumberItems(actualnumberBooks,0);
        shoppingCartPage.addNumberItems(actualnumberBooks,1);

        actualSubtotalItems = shoppingCartPage.getItemCount();

        Assert.assertEquals(actualSubtotalItems,actualnumberBooks *2);

        shoppingCartPage.deleteItem(0);

        actualSubtotalItems = shoppingCartPage.getItemCount();

        Assert.assertEquals(actualSubtotalItems,actualnumberBooks);

        shoppingCartPage.proceedCheckout();

        Assert.assertTrue(singInPage.emailFieldIsPresent());
        String actualUrl = WebDriverRunner.url();
       Assert.assertTrue(Objects.requireNonNull(actualUrl).contains("signin"));

    }

}
