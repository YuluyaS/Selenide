package ru.netology.web;

import com.codeborne.selenide.Condition;
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


class CardDeliveryTest {
    public String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void makingCardApplicationDelivery() {

        //Selenide.open( "http://localhost:9999/");
        Selenide.open("http://localhost:9999/");

        $("[data-test-id='city'] input").val("Саратов");

        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").shouldBe(visible).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);

        $("[data-test-id='date'] input").val(planningDate);

        $("[data-test-id='name'] input").val("Святынина Юлия");

        $("[data-test-id='phone'] input").val("+79272230350");

        $("[data-test-id='agreement']").click();

        $$("button").find(exactText("Забронировать")).click();
        $(Selectors.withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

        String expectedResponse = "Встреча успешно забронирована на "+planningDate;

        $(".notification__content").shouldBe(visible);
        $(".notification__content").shouldHave(exactText(expectedResponse));
    }
}