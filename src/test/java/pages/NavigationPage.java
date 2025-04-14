package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NavigationPage {

    @Step("Найти продукт '{0}'")
    public SelenideElement findProduct(String productName) {
        $("#js-vv21-search__search-input").click();
        $("#js-vv21-search__search-input").setValue(productName).click();

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
        }

        ElementsCollection cards = $$(".ProductCard");
        cards.first().shouldHave(Condition.text(productName));

        return cards.first();
    }

    @Step("Перейти на страницу продукта")
    public void pickProduct(SelenideElement product) {
        product.scrollTo().find("a.HeaderSearchBlockProd__TitleLink").click();
    }

    @Step("Выбрать магазин для самовывоза")
    public void pickAddress(String address) {
        $("h1").scrollTo();
        $(Selectors.withText("Выбрать магазин")).shouldBe(Condition.visible).click();
        $(".VV23_RWayModal_Main__AddAddressBtn").shouldBe(Condition.visible).click();

        $("#js-my-addresses-shop-address").scrollTo().setValue(address);

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
        }

        ElementsCollection options = $$(".VV_Dropdown__option");
        SelenideElement element = options.findBy(Condition.visible);
        if (element.isDisplayed()) element.click();

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
        }

        $("#js-my-addresses-addresses-shop-new .VV_RWayAdressesAddModal__ButtonWrp button")
                .shouldBe(Condition.visible).click();
    }

    @Step("Выбирать время доставки")
    public void pickDeliveryTime() {
        $$(".VV_Radio._separ._circleRight").first().click();
    }

    @Step("Перейти в список магазинов")
    public void pickShops() {
        $(".HeaderTopMenu__Item._shops").lastChild().shouldHave(Condition.href("/shops/")).click();
    }

    @Step("Выбрать регион {region}")
    public void selectRegion(String region) {
        $(".VV_Dropdown__container.js-dropdown-container").click();
        $$(".VV_Dropdown__option.js-dropdown-option").findBy(Condition.text(region))
                .shouldBe(Condition.visible).click();
    }

    @Step("Найти город {city} для выбранного региона")
    public void selectCity(String city) {
        $(".js-vv21-shops-panel-search__input").click();
        $(".js-vv21-shops-panel-search__input").setValue(city);

        // ждем подгрузки элементов dropdown для введенной строки (city)
        try {
            Thread.sleep(2000L);
        } catch (Exception e) {
        }
    }

    @Step("Выбирать магазин по адресу {street}")
    public void selectShopByStreet(String street) {
        $$(".VV21_MapPanelShops__Item").findBy(Condition.text(street)).click();
    }
}
