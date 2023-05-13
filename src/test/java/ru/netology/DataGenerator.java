package ru.netology;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import ru.netology.RegistrationInfo;

import java.time.LocalDate;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

@UtilityClass
public class DataGenerator {
    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(faker.address().city(),
                    LocalDate.now().plusDays(5), faker.name().fullName(),
                    faker.numerify("+###########"));

        }

    }

}
