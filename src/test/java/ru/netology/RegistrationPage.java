package ru.netology;

import org.openqa.selenium.Keys;
import ru.netology.RegistrationInfo;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    public void login(RegistrationInfo info) {
        String planningDate = info.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input.input__control").setValue("Владивосток");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(String.valueOf(planningDate));
        $("[data-test-id='name'] .input__control").setValue(info.getName());
        $("[data-test-id='phone'] .input__control").setValue(info.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button_view_extra").click();
        $("[data-test-id='notification'")
                .should(appear, Duration.ofSeconds(15))
                .shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }
}
