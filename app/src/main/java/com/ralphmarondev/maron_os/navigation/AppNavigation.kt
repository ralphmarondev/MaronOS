package com.ralphmarondev.maron_os.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.maron_os.launcher.presentation.LauncherScreen
import com.ralphmarondev.maron_os.splash.presentation.SplashScreen
import com.ralphmarondev.setup.SetupNavigation

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        composable<Routes.Splash> {
            SplashScreen(
                onSplashDone = {
                    navController.navigate(Routes.Setup) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.Setup> {
            SetupNavigation()
        }
        composable<Routes.Auth> {

        }
        composable<Routes.Launcher> {
            LauncherScreen()
        }
    }
}