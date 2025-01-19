package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class MainPage {
    private SelenideElement menu = $(byText("Меню")),
            catalog = $(byText("Каталог")),
            newProducts = $(byText("Новинки")),
            recypes = $(byTagAndText("div", "Рецепты")),
            vacancys = $(byText("Вакансии")),
            cart = $(byText("Корзина"));

    @Step("Открыть попап Меню")
    public void clickMenu() {
        menu.hover().parent();
    }

    @Step("Открыть попап Каталог")
    public MainPage hoverCatalog() {
        catalog.hover().ancestor("div");
        return this;

    }

    @Step("Перейти во вкладку Новинки")
    public void clickNewProducts() {
        $("ul.HeaderMainDMenuSmall").shouldBe(visible);
        newProducts.click();
    }

    @Step("Перейти на страницу рецептов")
    public void clickRecypes() {
        recypes.click();
    }

    @Step("Перейти на страницу вокансий")
    public void clickVacancys() {
        vacancys.click();
    }

    @Step("Перейти в корзину")
    public void clickCart() {
        cart.click();
    }

}

