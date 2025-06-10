package com.ralphmarondev.auth

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.auth.presentation.login.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object Routes {

    @Serializable
    data object Login
}

@Composable
fun AuthNavigation(
    onLoginSuccessful: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login
    ) {
        composable<Routes.Login> {
            LoginScreen(
                onLoginSuccessful = onLoginSuccessful
            )
        }
    }
}

