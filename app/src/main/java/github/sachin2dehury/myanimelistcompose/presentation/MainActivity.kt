package github.sachin2dehury.myanimelistcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.myanimelistcompose.domain.orZero
import github.sachin2dehury.myanimelistcompose.presentation.detail.DetailScreen
import github.sachin2dehury.myanimelistcompose.presentation.paginated.PaginatedScreen
import github.sachin2dehury.myanimelistcompose.ui.theme.MyAnimeListComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAnimeListComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "paginated",
                    ) {
                        composable("paginated") {
                            PaginatedScreen(navController = navController)
                        }
                        composable(
                            "detail/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) {
                            DetailScreen(id = it.arguments?.getInt("id").orZero())
                        }
                    }
                }
            }
        }
    }
}