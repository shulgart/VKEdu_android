package com.example.helloworld.presentation.appcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloworld.R
import com.example.helloworld.domain.appcard.AppCategory
import com.example.helloworld.domain.appcard.AppCard
import androidx.compose.foundation.lazy.items
import com.example.helloworld.presentation.theme.VkEducationTheme

@Composable
fun AppCardContent(list: List<AppCard>,
                   onClickLogo: () -> Unit,
                   onGoForward: (String) -> Unit,
                   modifier: Modifier) {
    Column(
        modifier
    ) {
        AppCardHeader(onClickLogo)
        LazyColumn(modifier) {
            items(list) { app ->
                AppRow(app, onGoForward)
            }
        }
//        for (app in list) {
//            AppRow(app, onGoForward)
//        }
    }
}

//@Preview
//@Composable
//private fun Preview()
//{
//    val browserApp = AppCard(
//        "Яндекс.Браузер - с Алисой",
//        "Быстрый и безопасный браузер",
//        AppCategory.TOOLS,
//        R.drawable.yandex_brow
//    )
//
//    val sberApp = AppCard(
//        "СберБанк Онлайн - с Салютом",
//        "Больше чем банк",
//        AppCategory.FINANCES,
//        R.drawable.sber
//    )
//
//    val mailApp = AppCard(
//        "Почта Mail.ru",
//        "Почтовый клиент для любых ящиков",
//        AppCategory.TOOLS,
//        R.drawable.mail_logo
//    )
//
//    val navApp = AppCard(
//        "Яндекс Навигатор",
//        "Парковки и заправки - по пути",
//        AppCategory.TRANSPORT,
//        R.drawable.navigator
//    )
//
//    val mtsApp = AppCard(
//        "Мой МТС",
//        "Мой МТС - центр экосистемы МТС",
//        AppCategory.TOOLS,
//        R.drawable.mts
//    )
//
//    val yandexApp = AppCard(
//        "Яндекс - с Алисой",
//        "Яндекс - поиск всегда под рукой",
//        AppCategory.TOOLS,
//        R.drawable.yandex
//    )
//
//    VkEducationTheme() {
//        AppCardContent(
//            list = listOf(browserApp, sberApp, mailApp, navApp, mtsApp, yandexApp),
//            {},
//            {},
//            modifier=Modifier
//            .background(Color.LightGray)
//            .fillMaxHeight()
//            .safeDrawingPadding())
//    }
//}