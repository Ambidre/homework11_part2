package gmail.anastasiacoder.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GitHubTest extends TestBase {
    @Test
    @DisplayName("Регистрация с коротким паролем")
    public void signUpWithShortPassTest() {
        step("Открыть сайт github.com/join", () ->
            open("https://github.com/join"));
        step("Ввести короткий пароль", () ->
            $("#user_password").setValue("qwerty"));
        step("Проверить что отобразилась ошибка", () ->
            $(".error").shouldHave(Condition.exactText("Password is too short (minimum is 8 characters), " +
                "needs at least 1 number, and is in a list of passwords commonly used on other websites")));
    }
}