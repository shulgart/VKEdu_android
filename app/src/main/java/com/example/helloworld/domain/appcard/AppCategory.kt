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

    @SerialName("Общение")
    SOCIAL("Общение"),

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

    @SerialName("Здоровье и фитнес")
    HEALTH("Здоровье и фитнес"),

    @SerialName("Спорт")
    SPORTS("Спорт"),

    @SerialName("Новости")
    NEWS("Новости"),

    @SerialName("Книги и справочники")
    BOOKS("Книги и справочники"),

    @SerialName("Бизнес")
    BUSINESS("Бизнес"),

    @SerialName("Финансы")
    FINANCE("Финансы"),

    @SerialName("Путешествия")
    TRAVEL("Путешествия"),

    @SerialName("Карты")
    MAPS("Карты"),

    @SerialName("Еда и напитки")
    FOOD("Еда и напитки"),

    @SerialName("Шопинг")
    SHOPPING("Шопинг"),

    @SerialName("Утилиты")
    UTILITIES("Утилиты"),

    @SerialName("Фото и видео")
    PHOTOVIDEO("Фото и видео"),

    @SerialName("Образ жизни")
    LIFESTYLE("Образ жизни"),

    @SerialName("Навигация")
    NAVIGATION("Навигация"),

    @SerialName("Погода")
    WEATHER("Погода");
    companion object {
        // Эта функция заменит падающий valueOf
        fun fromString(value: String): AppCategory {
            return entries.find { it.value == value } ?: APP
        }
    }
}