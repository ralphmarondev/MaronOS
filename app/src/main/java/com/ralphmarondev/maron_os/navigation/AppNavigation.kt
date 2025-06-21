package com.ralphmarondev.maron_os.navigation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.delay

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: Any
) {
    val context = LocalContext.current
    var backPressedOnce by remember { mutableStateOf(false) }

    LaunchedEffect(backPressedOnce) {
        if (backPressedOnce) {
            delay(2000)
            backPressedOnce = false
        }
    }

    BackHandler(enabled = true) {
        if (backPressedOnce) {
            android.os.Process.killProcess(android.os.Process.myPid())
        } else {
            backPressedOnce = true
            Toast.makeText(context, "Press back again to exit.", Toast.LENGTH_SHORT).show()
        }
    }

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