package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryRequestTest {
    @BeforeEach
    void setup(){
        open("http://localhost:9999/");
    }
    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationInfo info = DataGenerator.Registration.generateInfo("ru");

    @Test
    public void shouldMakeRequestPositive() {
        registrationPage.login(info);
    }
        @Test
    public void shouldChangeDate() {
        registrationPage.login(info);
        registrationPage.replan(11);
        }
}
