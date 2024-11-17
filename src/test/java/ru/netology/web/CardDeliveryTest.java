package ru.netology.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;

class CardDeliveryTest {
    public String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void MakingCardApplicationDelivery() {

        Selenide.open( "http://localhost:9999/");

        $$(".form").filter(visible).first().click();
        $$("#fieldset").first().click();
        $(Selectors.withText());
        $("[data-test-id='city']").shouldBe(visible).click();
        $("[data-test-id='city']").shouldBe(visible).sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='city']").clear();
        $("[data-test-id='city']").setValue("Саратов");



        String planningDate = generateDate(3, "dd.MM.yyyy");
        $$("[data-test-id='date']").filter(visible).first().sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $$("[data-test-id='date']").filter(visible).first().setValue(planningDate);

        $("[data-test-id='name']").setValue("Святынина Юлия");

        $("[data-test-id='phone']").setValue("+79272230350");

        $$("[data-test-id='agreement']").last().setValue("Я соглашаюсь с условиями обработки и использования моих персональных данных");

        $$("button").find(exactText("Забронировать")).click();
        $(Selectors.withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
    }

}