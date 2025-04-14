import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import pages.MainPage;
import helpers.AssertHelper;
import pages.NavigationPage;

import static com.codeborne.selenide.Selenide.$$;

public class VkusvillTests extends TestBase {
    AssertHelper assertion = new AssertHelper();
    MainPage mainPage = new MainPage();
    NavigationPage navigationPage = new NavigationPage();

    @DisplayName("Проверить работу попапа 'Меню'")
    @Test
    @Tag("Medium")
    void menuPopupCheckTest() {
        mainPage.clickMenu();
        assertion.textExist("Медиа");
    }

    @DisplayName("Проверить работу попапа 'Каталог'")
    @Test
    @Tag("Medium")
    void catalogPopupCheckTest() {
        mainPage.hoverCatalog();
        mainPage.clickNewProducts();
        assertion.textExist("Новинки");
    }

    @DisplayName("Проверить переход на страницу рецептов")
    @Test
    @Tag("Medium")
    void recipesPageCheckTest() {
        mainPage.clickRecipes();
        assertion.textExist("Рецепты");
    }

    @DisplayName("Проверить переход на страницу вакансий")
    @Test
    @Tag("Medium")
    void vacanciesPageCheckTest() {
        mainPage.clickVacancies();
        assertion.textExist("Работа");
    }

    @DisplayName("Проверить переход в корзину")
    @Test
    @Tag("Medium")
    void cartPageCheckTest() {
        mainPage.clickCart();
        assertion.textExist("Корзина");
    }

    @DisplayName("Проверить поиск и переход на карточку продукта")
    @Test
    @Tag("High")
    public void findAndPickProduct() {
        SelenideElement product = navigationPage.findProduct("Молоко");
        Assertions.assertNotNull(product);

        navigationPage.pickProduct(product);

        assertion.h1Exist("Молоко");
        assertion.addShoppingCardAvailable();
    }

    @DisplayName("Проверить выбор магазина для самовывоза и времени готовности заказа через карточку продукта")
    @Test
    @Tag("High")
    public void testPickAddressAndDeliveryTime_viaProductDetailsScreen() {
        SelenideElement product = navigationPage.findProduct("Молоко");
        navigationPage.pickProduct(product);

        navigationPage.pickAddress("Рыбинск, Крестовая улица, 41");

        navigationPage.pickDeliveryTime();

        assertion.existElementWithText(".HeaderATDToggler__Link.js-delivery__shopselect--form-show",
                "Крестовая");
    }

    @DisplayName("Проверить поиск магазина по адресу")
    @Test
    @Tag("High")
    public void testFindShopByAddress() {
        navigationPage.pickShops();
        navigationPage.selectRegion("Ярославль");
        navigationPage.selectCity("Рыбин");

        assertion.numberOfElements(2, $$(".VV21_MapPanelShops__Item"));

        navigationPage.selectShopByStreet("Крест");

        assertion.existElementWithText(".VV21_MapPanelCard__Phone", "121-32-65");
    }
}
