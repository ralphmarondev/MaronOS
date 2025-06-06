package com.ralphmarondev.maron_os.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.setup.navigation.SetupNavigation

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Setup
    ) {
        composable<Routes.Setup> {
            SetupNavigation(
                install = {
                    navController.navigate(Routes.Auth) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.Auth> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Auth",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
                OutlinedButton(
                    onClick = {
                        navController.navigate(Routes.Setup) {
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text(
                        text = "Navigate to Setup"
                    )
                }
                Button(
                    onClick = {
                        navController.navigate(Routes.Launcher) {
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text(
                        text = "Navigate to Launcher"
                    )
                }
            }
        }
        composable<Routes.Launcher> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Launcher",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
                OutlinedButton(
                    onClick = {
                        navController.navigate(Routes.Setup) {
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text(
                        text = "Navigate to Setup"
                    )
                }
                Button(
                    onClick = {
                        navController.navigate(Routes.Auth) {
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text(
                        text = "Navigate to Auth"
                    )
                }
            }
        }
    }
}