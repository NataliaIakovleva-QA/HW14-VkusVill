import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    public static final String remoteUrl = System.getProperty("remoteUrl", "selenoid.autotests.cloud");

    @BeforeEach
    public void baseTest() {

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "126.0");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://vkusvill.ru/yar/";
        Configuration.remote = "https://user1:1234@" + remoteUrl + "/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", System.getProperty("browser"));
        capabilities.setCapability("browserVersion",System.getProperty("browserVersion"));
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(baseUrl);
    }


    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        if (!Configuration.browser.equalsIgnoreCase("firefox")) {
            Attach.browserConsoleLogs();
        }
        Attach.addVideo();

        Selenide.closeWebDriver();
    }
    }
