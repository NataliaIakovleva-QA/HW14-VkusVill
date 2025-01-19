import org.junit.jupiter.api.Test;
import pages.MainPage;
import steps.Assertion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

public class VkusvilTest extends BaseTest {
    Assertion assertion = new Assertion();
    MainPage mainPage = new MainPage();

    @DisplayName("проверить работу попапа 'Меню'")
    @Test
    @Tags({@Tag("High"), @Tag("web")})
    void menuPopupCheck() {
        mainPage.clickMenu();
        assertion.textExist("Медиа");
    }

    @DisplayName("проверить работу попапа 'Каталог'")
    @Test
    @Tags({@Tag("High"), @Tag("web")})
    void catalogPopupCheck() {
        mainPage.hoverCatalog();
        mainPage.clickNewProducts();
        assertion.textExist("Новинки");
    }

    @DisplayName("проверить переход на страницу рецептов")
    @Test
    @Tags({@Tag("Medium"), @Tag("web")})
    void recipesPageCheck() {
        mainPage.clickRecypes();
        assertion.textExist("Рецепты");
    }

    @DisplayName("проверить переход на страницу вакансий")
    @Test
    @Tags({@Tag("High"), @Tag("web")})
    void vacancysPageCheck() {
        mainPage.clickVacancys();
        assertion.textExist("Работа");
    }

    @DisplayName("Проверить переход в корзину")
    @Test
    @Tags({@Tag("Blocker"), @Tag("web")})
    void cartPageCheck() {
        mainPage.clickCart();
        assertion.textExist("Корзина");
    }
}
