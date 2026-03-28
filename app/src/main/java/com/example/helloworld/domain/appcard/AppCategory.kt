package com.example.helloworld.domain.appcard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
enum class AppCategory(val value: String) {
    @SerialName("Приложения")
    APP("Приложения"),

    @SerialName("Игры")
    GAME("Игры"),

    @SerialName("Производительность")
    PRODUCTIVITY("Производительность"),

    @SerialName("Социальные сети")
    SOCIAL("Социальные сети"),

    @SerialName("Образование")
    EDUCATION("Образование"),

    @SerialName("Развлечения")
    ENTERTAINMENT("Развлечения"),

    @SerialName("Музыка")
    MUSIC("Музыка"),

    @SerialName("Видео")
    VIDEO("Видео"),

    @SerialName("Фотография")
    PHOTOGRAPHY("Фотография"),

    @SerialName("Здоровье")
    HEALTH("Здоровье"),

    @SerialName("Спорт")
    SPORTS("Спорт"),

    @SerialName("Новости")
    NEWS("Новости"),

    @SerialName("Книги")
    BOOKS("Книги"),

    @SerialName("Бизнес")
    BUSINESS("Бизнес"),

    @SerialName("Финансы")
    FINANCE("Финансы"),

    @SerialName("Путешествия")
    TRAVEL("Путешествия"),

    @SerialName("Карты")
    MAPS("Карты"),

    @SerialName("Еда")
    FOOD("Еда"),

    @SerialName("Покупки")
    SHOPPING("Покупки"),

    @SerialName("Утилиты")
    UTILITIES("Утилиты");
    companion object {
        // Эта функция заменит падающий valueOf
        fun fromString(value: String): AppCategory {
            return entries.find { it.value == value } ?: APP
        }
    }
}