package com.ralphmarondev.setup

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.setup.presentation.AppChoicesScreen
import com.ralphmarondev.setup.presentation.CompletedScreen
import com.ralphmarondev.setup.presentation.EncryptionScreen
import com.ralphmarondev.setup.presentation.InstallationTypeScreen
import com.ralphmarondev.setup.presentation.InstallingScreen
import com.ralphmarondev.setup.presentation.LoginDetailsScreen
import com.ralphmarondev.setup.presentation.SummaryScreen
import com.ralphmarondev.setup.presentation.ThemeChoicesScreen
import com.ralphmarondev.setup.presentation.WelcomeScreen

@Composable
fun SetupNavigation(
    onSetupCompleted: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Welcome
    ) {
        composable<Routes.Welcome> {
            WelcomeScreen(
                navigateToInstallationType = {
                    navController.navigate(Routes.InstallationType) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.InstallationType> {
            InstallationTypeScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToAppChoices = {
                    navController.navigate(Routes.AppChoices) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.AppChoices> {
            AppChoicesScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToThemeChoices = {
                    navController.navigate(Routes.ThemeChoices) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.ThemeChoices> {
            ThemeChoicesScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToEncryption = {
                    navController.navigate(Routes.Encryption) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.Encryption> {
            EncryptionScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToLoginDetails = {
                    navController.navigate(Routes.LoginDetails) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.LoginDetails> {
            LoginDetailsScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToSummary = {
                    navController.navigate(Routes.Summary) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.Summary> {
            SummaryScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToInstalling = {
                    navController.navigate(Routes.Installing) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.Installing> {
            InstallingScreen(
                navigateToCompleted = {
                    navController.navigate(Routes.Completed) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.Completed> {
            CompletedScreen(
                completed = onSetupCompleted
            )
        }
    }
}