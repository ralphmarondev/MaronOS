package com.ralphmarondev.auth.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    onLoginSuccessful: () -> Unit
) {
    val viewModel: LoginViewModel = koinViewModel()
    val username = viewModel.username.collectAsState().value
    val password = viewModel.password.collectAsState().value
    val response = viewModel.response.collectAsState().value

    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(response) {
        response?.let {
            if (it.success == true) {
                onLoginSuccessful()
            }
            snackbarState.showSnackbar(
                message = it.message
            )
            viewModel.resetResponse()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarState)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                OutlinedCard {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        OutlinedTextField(
                            value = username,
                            onValueChange = viewModel::onUsernameValueChange,
                            label = {
                                Text(
                                    text = "Username"
                                )
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = viewModel::onPasswordValueChange,
                            label = {
                                Text(
                                    text = "Password"
                                )
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = viewModel::login,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "LOGIN"
                            )
                        }
                    }
                }
            }
        }
    }
}