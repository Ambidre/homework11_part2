package gmail.anastasiacoder.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import gmail.anastasiacoder.config.CredentialsConfig;
import gmail.anastasiacoder.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static okhttp3.internal.Util.format;

public class TestBase {

    static CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
    static String login = credentials.login();
    static String password = credentials.password();
    static String url = System.getProperty("url", "selenoid.autotests.cloud/wd/hub/");

    @BeforeAll
    static void beforeAll() {


        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browserSize = "1920x1080";
        Configuration.remote = format("https://%s:%s@%s", login, password, url);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}