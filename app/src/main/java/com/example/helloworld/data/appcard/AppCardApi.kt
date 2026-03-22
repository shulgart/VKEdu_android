package com.example.helloworld.data.appcard

import com.example.helloworld.R
import com.example.helloworld.domain.appcard.AppCategory
import com.example.helloworld.domain.appcard.AppCard
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class AppCardApi {
    // В будущем заменим этот метод на вызов API.
    suspend fun get(): List<AppCardDto> {
        delay(1.seconds)

        val browserApp = AppCardDto(
            "Яндекс.Браузер - с Алисой",
            "Быстрый и безопасный браузер",
            "Инструменты",
            R.drawable.yandex_brow
        )

        val sberApp = AppCardDto(
            "СберБанк Онлайн - с Салютом",
            "Больше чем банк",
            "Финансы",
            R.drawable.sber
        )

        val mailApp = AppCardDto(
            "Почта Mail.ru",
            "Почтовый клиент для любых ящиков",
            "Инструменты",
            R.drawable.mail_logo
        )

        val navApp = AppCardDto(
            "Яндекс Навигатор",
            "Парковки и заправки - по пути",
            "Транспорт",
            R.drawable.navigator
        )

        val mtsApp = AppCardDto(
            "Мой МТС",
            "Мой МТС - центр экосистемы МТС",
            "Инструменты",
            R.drawable.mts
        )

        val yandexApp = AppCardDto(
            "Яндекс - с Алисой",
            "Яндекс - поиск всегда под рукой",
            "Инструменты",
            R.drawable.yandex
        )

        val appCards = listOf(browserApp, sberApp, mailApp, navApp, mtsApp, yandexApp)

        return appCards
    }
}