package ru.netology;

import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    public void login(RegistrationInfo info) {
        String planningDate = info.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input.input__control").setValue("Владивосток");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] .input__control").setValue(info.getName());
        $("[data-test-id='phone'] .input__control").setValue(info.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button_view_extra").click();
        $("[data-test-id='success-notification']")
                .should(appear, Duration.ofSeconds(15))
                .shouldHave(text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15));
    }
    public void replan(int daysToAdd) {
        String planningDate = LocalDate.parse($("[data-test-id='date'] input").getValue(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                .plusDays(daysToAdd).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='success-notification'] .icon-button").shouldBe(visible, Duration.ofSeconds(15)).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("button.button_view_extra").click();
        $("[data-test-id='replan-notification'] .button").click();
        $("[data-test-id='success-notification']")
                .should(appear, Duration.ofSeconds(15))
                .shouldHave(text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15));
    }
}

