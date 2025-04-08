import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import steps.MainPageHelper;
import steps.AssertHelper;
import steps.NavigationHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VkusvillTests extends TestBase {
    AssertHelper assertion = new AssertHelper();
    MainPageHelper mainPage = new MainPageHelper();
    NavigationHelper pickAddress = new NavigationHelper();

    @DisplayName("Проверить работу попапа 'Меню'")
    @Test
    @Tags({@Tag("High"), @Tag("web")})
    void menuPopupCheckTest() {
        mainPage.clickMenu();
        assertion.textExist("Медиа");
    }

    @DisplayName("Проверить работу попапа 'Каталог'")
    @Test
    @Tags({@Tag("High"), @Tag("web")})
    void catalogPopupCheckTest() {
        mainPage.hoverCatalog();
        mainPage.clickNewProducts();
        assertion.textExist("Новинки");
    }

    @DisplayName("Проверить переход на страницу рецептов")
    @Test
    @Tags({@Tag("Medium"), @Tag("web")})
    void recipesPageCheckTest() {
        mainPage.clickRecipes();
        assertion.textExist("Рецепты");
    }

    @DisplayName("Проверить переход на страницу вакансий")
    @Test
    @Tags({@Tag("Medium"), @Tag("web")})
    void vacanciesPageCheckTest() {
        mainPage.clickVacancies();
        assertion.textExist("Работа");
    }

    @DisplayName("Проверить переход в корзину")
    @Test
    @Tags({@Tag("Medium"), @Tag("web")})
    void cartPageCheckTest() {
        mainPage.clickCart();
        assertion.textExist("Корзина");
    }

    @DisplayName("Проверить поиск и переход на карточку продукта")
    @Test
    @Tags({@Tag("High"), @Tag("web")})
    public void findAndPickProduct() {
        SelenideElement product = pickAddress.findProduct("Молоко");
        Assertions.assertNotNull(product);

        pickAddress.pickProduct(product);

        assertion.h1Exist("Молоко");
        assertion.addShoppingCardAvailable();
    }

    @DisplayName("Проверить выбор магазина для самовывоза и времени готовности заказа через карточку продукта")
    @Test
    @Tags({@Tag("High"), @Tag("web")})
    public void testPickAddressAndDeliveryTime_viaProductDetailsScreen() {
        SelenideElement product = pickAddress.findProduct("Молоко");
        pickAddress.pickProduct(product);

        pickAddress.pickAddress("Рыбинск, Крестовая улица, 41");

        pickAddress.pickDeliveryTime();

        assertion.existElementWithText(".HeaderATDToggler__Link.js-delivery__shopselect--form-show",
                "Крестовая");
    }

    @DisplayName("Проверить поиск магазина по адресу")
    @Test
    @Tags({@Tag("High"), @Tag("web")})
    public void testFindShopByAddress() {
        pickAddress.pickShops();
        pickAddress.selectRegion("Ярославль");
        pickAddress.selectCity("Рыбин");

        assertion.numberOfElements(2, $$(".VV21_MapPanelShops__Item"));

        pickAddress.selectShopByStreet("Крест");

        assertion.existElementWithText(".VV21_MapPanelCard__Phone", "121-32-65");
    }
}
