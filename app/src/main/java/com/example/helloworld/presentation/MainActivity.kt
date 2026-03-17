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
import com.example.helloworld.presentation.appcard.AppCardScreen
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

    NavHost(navController = navController, startDestination = "cards") {
        // Определение первого экрана
        composable("cards") {
            AppCardScreen(onGoForward = { navController.navigate("details") })
        }
        // Определение второго экрана
        composable("details") {
            AppDetailsScreen(onBack = { navController.popBackStack() })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScenePreview() {
    VkEducationTheme {
        AppCardScreen({})
    }
}