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
        open("http://localhost:9999/");

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
        registrationPage.replan(info);
        }
}
