package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryRequestTest {
    @BeforeEach
    void setup(){
        open("http://0.0.0.0:9999");

    }

    public String generationDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldMakeRequestPositive() {
        RegistrationPage registrationPage = new RegistrationPage();
        RegistrationInfo info = DataGenerator.Registration.generateInfo("ru");
        registrationPage.login(info);
    }
        @Test
    public void shouldChangeDate() {
        Configuration.holdBrowserOpen = true;
        String planningDate = generationDate(13);
        RegistrationPage registrationPage = new RegistrationPage();
        RegistrationInfo info = DataGenerator.Registration.generateInfo("ru");
        registrationPage.login(info);
        $("[data-test-id='notification'] .icon-button").click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("button.button_view_extra").click();
        $("[data-test-id='notification'")
                    .should(appear, Duration.ofSeconds(15))
                    .shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));

        }
}
