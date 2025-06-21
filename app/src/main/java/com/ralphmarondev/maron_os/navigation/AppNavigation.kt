package com.ralphmarondev.maron_os.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.auth.AuthNavigation
import com.ralphmarondev.browser.BrowserNavigation
import com.ralphmarondev.maron_os.launcher.presentation.LauncherScreen
import com.ralphmarondev.maron_os.splash.presentation.SplashScreen
import com.ralphmarondev.notes.NotesNavigation
import com.ralphmarondev.settings.SettingsNavigation
import com.ralphmarondev.setup.SetupNavigation

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: Any
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Routes.Splash> {
            SplashScreen(
                onSplashDone = {
                    navController.navigate(Routes.Auth) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.Setup> {
            SetupNavigation(
                onSetupCompleted = {
                    navController.navigate(Routes.Auth) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.Auth> {
            AuthNavigation(
                onLoginSuccessful = {
                    navController.navigate(Routes.Launcher) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.Launcher> {
            LauncherScreen(navController = navController)
        }
        composable<Routes.Settings> {
            SettingsNavigation()
        }
        composable<Routes.Browser> {
            BrowserNavigation()
        }
        composable<Routes.Notes> {
            NotesNavigation()
        }
    }
}