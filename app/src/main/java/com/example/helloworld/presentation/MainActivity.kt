package com.example.helloworld.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.helloworld.presentation.appcard.AppCardScreen
import com.example.helloworld.presentation.appcard.AppCardViewModel
import com.example.helloworld.presentation.appdetails.AppDetailsScreen
import com.example.helloworld.presentation.appdetails.AppDetailsViewModel
import com.example.helloworld.presentation.theme.VkEducationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            val viewModel: AppCardViewModel = hiltViewModel()
            AppCardScreen(viewModel, onGoForward = { id -> navController.navigate("details/$id") })
        }
        // Определение второго экрана
        composable(
            "details/{id}",
            listOf(navArgument("id") { type = NavType.StringType }
        )) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val viewModel: AppDetailsViewModel = hiltViewModel()
            AppDetailsScreen(viewModel, onBack = { navController.popBackStack() })
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ScenePreview() {
//    VkEducationTheme {
//        AppCardScreen()
//    }
//}