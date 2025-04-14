package helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AssertHelper {

    @Step("Проверить наличие Текста {value}")
    public void textExist(String value) {
        $(withText(value)).should(Condition.exist);
    }

    @Step("Проверить наличие заголовка H1 с текстом {value}")
    public void h1Exist(String value) {
        $$("h1").findBy(Condition.text(value)).shouldBe(Condition.visible);
    }

    @Step("Проверить количество элементов в коллекции")
    public void numberOfElements(int expectedSize, ElementsCollection elements) {
        Assertions.assertEquals(expectedSize, elements.size());
    }

    @Step("Проверить наличие текста {expectedText} внутри элемента {cssSelector}")
    public void existElementWithText(String cssSelector, String expectedText) {
        $(cssSelector).shouldBe(Condition.visible);
        $(cssSelector).shouldHave(Condition.text(expectedText));
    }

    public void addShoppingCardAvailable() {
        $("h1").scrollTo();
        $(".VV23_CartButtonContainer").
                $(withText("В корзину")).shouldBe(Condition.visible);
    }
}

