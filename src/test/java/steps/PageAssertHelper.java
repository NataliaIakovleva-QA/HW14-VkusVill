package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class PageAssertHelper {


    @Step("Проверить наличие Текста {value}")
    public void textExist(String value) {
        $(withText(value)).should(Condition.exist);
    }
}

