package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.myapplication.presentation.screen.history.HistoryScreenRoot
import com.example.myapplication.presentation.screen.history.HistoryViewModel
import com.example.myapplication.presentation.screen.main.MainScreenRoot
import com.example.myapplication.presentation.screen.main.MainViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                        val navController = rememberNavController()
                        NavHost(
                            modifier = Modifier
                                .fillMaxSize(),
                            navController = navController,
                            startDestination = "nav_graph",
                            enterTransition = { EnterTransition.None },
                            exitTransition  = { ExitTransition.None },
                            popEnterTransition   = { EnterTransition.None },
                            popExitTransition    = { ExitTransition.None },
                        ) {
                            navigation(
                                route = "nav_graph",
                                startDestination = "main",
                            ) {
                                composable("main") {
                                    val viewModel = koinInject<MainViewModel>()
                                    MainScreenRoot(
                                        viewModel = viewModel,
                                        navigateToHistory = {
                                            navController.navigate("history")
                                        }
                                    )
                                }
                                composable("history") {
                                    val viewModel = koinInject<HistoryViewModel>()
                                    HistoryScreenRoot(
                                        viewModel = viewModel,
                                        navigateBack = {
                                            navController.navigateUp()
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
