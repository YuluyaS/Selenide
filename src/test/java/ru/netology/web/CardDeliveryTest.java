package ru.netology.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Assertions;
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
        $("[placeholder='Город']").shouldBe(visible).click();
        $("[placeholder='Город']").setValue("Саратов");

        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[placeholder='Дата встречи']").shouldBe(visible).click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(planningDate);

        $("[name='name']").setValue("Святынина Юлия");
        $("[name='phone']").setValue("+79272230350");

        $("[data-test-id='agreement']").click();

        $$("button").find(exactText("Забронировать")).click();
        $(Selectors.withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

        String expectedResponse = "Встреча успешно забронирована на "+planningDate;

        $(".notification__content").shouldBe(visible);
        String actualResponse = $(".notification__content").getText();
        //System.out.println("actualResponse ="+actualResponse);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}