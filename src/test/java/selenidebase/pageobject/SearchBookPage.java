package selenidebase.pageobject;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SearchBookPage {

    // Elementos de la página
    private final SelenideElement searchInput = $("[name='field-keywords']");
    private final SelenideElement searchButton = $("#nav-search-submit-button");
    private ElementsCollection searchResultList = $$("div[data-component-type='s-search-result']");
    private SelenideElement hardcoverFilter = $x("//span[text()='Hardcover']");
    private SelenideElement kindleEditionFilter = $x("//span[text()='Kindle Edition']");
    private SelenideElement paperBackFilter = $x("//span[text()='Paperback']");
    private SelenideElement cartButton = $("#nav-cart");

    // Método para buscar un producto
    public void searchProduct(String product) {
        searchInput.setValue(product);
        searchButton.click();
    }

    // Método para obtener la cantidad de resultados
    public int getResultProduct() {
        return searchResultList.size();
    }

    // Método para seleccionar un producto por su posición en la lista
    public void selectProductByPosition(int positionList) {
        if (positionList <= 0 || positionList > searchResultList.size()) {
            throw new IndexOutOfBoundsException("position " + positionList + " out of the range");
        }
        searchResultList.get(positionList - 1)
                .$x(".//div[@data-cy='title-recipe']")
                .click();
    }

    // Métodos para aplicar filtros
    public void applyHardcoverFilter() {
        hardcoverFilter.click();
    }

    public void applyKindleEditionFilter() {
        kindleEditionFilter.click();
    }

    public void applyPaperBackFilter() {
        paperBackFilter.click();
    }

    // Método para obtener el título del primer producto
    public String getTitleFirstProduct() {
        return searchResultList.first()
                .find(".//div[@data-cy='title-recipe']//h2")
                .shouldBe(visible)
                .getText();
    }

    // Método para contar libros de un tipo específico
    public int getBookWithType(String typeBook) {
        return (int) searchResultList.stream()
                .filter(element -> element.$x(".//a[contains(text(),'" + typeBook + "')]").exists())
                .count();
    }

    // Método para ir al carrito
    public void goCart() {
        cartButton.click();
    }
}