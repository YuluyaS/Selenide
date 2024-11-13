package ru.netology.web;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$$;

class CardDeliveryTest {


    @Test
    void MakingCardApplicationDelivery() {
     Selenide.open( relativeOrAbsoluteUrl:"http://0.0.0.0:9999/");
     $$ (cssSelector: ".tab-item").last().click();

  }
}
