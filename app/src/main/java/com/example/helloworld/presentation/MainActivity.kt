package com.example.helloworld.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helloworld.R
import com.example.helloworld.presentation.appcard.AppCardHeader
import com.example.helloworld.presentation.appcard.AppCategory
import com.example.helloworld.presentation.appcard.AppRow
import com.example.helloworld.presentation.appcard.AppShort
import com.example.helloworld.presentation.appdetails.AppDetailsScreen
import com.example.helloworld.presentation.theme.VkEducationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkEducationTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        // Определение первого экрана
        composable("home") {
            HomeScreen(onNavigateToDetails = { navController.navigate("details") })
        }
        // Определение второго экрана
        composable("details") {
            DetailsScreen(onBack = { navController.popBackStack() })
        }
    }
}

@Composable
fun DetailsScreen(onBack: () -> Unit) {
    AppDetailsScreen(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        onGoBack = onBack
    )
}

@Composable
fun HomeScreen(onNavigateToDetails: () -> Unit = {}) {
    val browserApp = AppShort(
        "Яндекс.Браузер - с Алисой",
        "Быстрый и безопасный браузер",
        AppCategory.TOOLS,
        R.drawable.yandex_brow
    )

    val sberApp = AppShort(
        "СберБанк Онлайн - с Салютом",
        "Больше чем банк",
        AppCategory.FINANCES,
        R.drawable.sber
    )

    val mailApp = AppShort(
        "Почта Mail.ru",
        "Почтовый клиент для любых ящиков",
        AppCategory.TOOLS,
        R.drawable.mail_logo
    )

    val navApp = AppShort(
        "Яндекс Навигатор",
        "Парковки и заправки - по пути",
        AppCategory.TRANSPORT,
        R.drawable.navigator
    )

    val mtsApp = AppShort(
        "Мой МТС",
        "Мой МТС - центр экосистемы МТС",
        AppCategory.TOOLS,
        R.drawable.mts
    )

    val yandexApp = AppShort(
        "Яндекс - с Алисой",
        "Яндекс - поиск всегда под рукой",
        AppCategory.TOOLS,
        R.drawable.yandex
    )

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxHeight()
            .safeDrawingPadding()
    ) {
        AppCardHeader()
        AppRow(sberApp, onNavigateToDetails)
        AppRow(browserApp, onNavigateToDetails)
        AppRow(mailApp, onNavigateToDetails)
        AppRow(navApp, onNavigateToDetails)
        AppRow(mtsApp, onNavigateToDetails)
        AppRow(yandexApp, onNavigateToDetails)
    }
}

@Preview(showBackground = true)
@Composable
fun ScenePreview() {
    VkEducationTheme {
        HomeScreen()
    }
}