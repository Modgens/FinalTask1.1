package com.example.tt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    Стосовно паттернів, я нічого не зміг сюди додати, окрім Білдера. Як на мене, паттерни проєктування
    створенні для рефакторинга складного коду, щоб зробити його більш ефективним та зрозумілим, але коли
    структура і код максимально прості в проєкті, я не бачу сенсу навантажувати його лишніми паттернами
    притягнутими в код за вуха, тому окрім Твірних паттернів таких як Білдер і Сінглтон я нічого більше
    не використав.
 */
@SpringBootApplication
public class TtApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtApplication.class, args);
    }

}
