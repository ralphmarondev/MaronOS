package com.ralphmarondev.setup.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.setup.presentation.AccountScreen
import com.ralphmarondev.setup.presentation.NotificationScreen
import com.ralphmarondev.setup.presentation.SummaryScreen
import com.ralphmarondev.setup.presentation.ThemeScreen
import com.ralphmarondev.setup.presentation.WelcomeScreen

@Composable
fun SetupNavigation(
    install: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Welcome
    ) {
        composable<Routes.Welcome> {
            WelcomeScreen(
                navigateToTheme = {
                    navController.navigate(Routes.Theme) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.Theme> {
            ThemeScreen(
                navigateToNotification = {
                    navController.navigate(Routes.Notification) {
                        launchSingleTop = true
                    }
                },
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Notification> {
            NotificationScreen(
                navigateToAccount = {
                    navController.navigate(Routes.Account) {
                        launchSingleTop = true
                    }
                },
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Account> {
            AccountScreen(
                navigateToSummary = {
                    navController.navigate(Routes.Summary) {
                        launchSingleTop = true
                    }
                },
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Summary> {
            SummaryScreen(
                install = install,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}