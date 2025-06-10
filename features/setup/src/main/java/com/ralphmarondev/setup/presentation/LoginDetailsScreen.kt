package com.ralphmarondev.setup.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountTree
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.presentation.components.NormalTextField
import com.ralphmarondev.presentation.components.PasswordTextField
import com.ralphmarondev.setup.R
import com.ralphmarondev.setup.SetupViewModel

@Composable
fun LoginDetailsScreen(
    navigateBack: () -> Unit,
    navigateToSummary: () -> Unit,
    viewModel: SetupViewModel
) {
    val fullName = viewModel.fullName.collectAsState().value
    val computerName = viewModel.computerName.collectAsState().value
    val username = viewModel.username.collectAsState().value
    val password = viewModel.password.collectAsState().value
    val confirmPassword = viewModel.confirmPassword.collectAsState().value
    val requirePasswordOnLogin = viewModel.requirePasswordOnLogin.collectAsState().value
    val response = viewModel.accountResponse.collectAsState().value

    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(response) {
        response?.let {
            if (it.success == true) {
                navigateToSummary()
            }
            snackbarState.showSnackbar(
                message = it.message
            )
            viewModel.resetAccountResponse()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.logo),
                contentDescription = "App logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Create your account",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            NormalTextField(
                value = fullName,
                onValueChange = viewModel::onFullNameValueChange,
                leadingIcon = Icons.Outlined.AccountBox,
                label = "Your name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            NormalTextField(
                value = computerName,
                onValueChange = viewModel::onComputerValueChange,
                leadingIcon = Icons.Outlined.Computer,
                label = "Your computer's name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            NormalTextField(
                value = username,
                onValueChange = viewModel::onUsernameValueChange,
                leadingIcon = Icons.Outlined.AccountTree,
                label = "Your username",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            PasswordTextField(
                value = password,
                onValueChange = viewModel::onPasswordValueChange,
                leadingIcon = Icons.Outlined.Password,
                label = "Password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            PasswordTextField(
                value = confirmPassword,
                onValueChange = viewModel::onConfirmPasswordValueChange,
                leadingIcon = Icons.Outlined.Password,
                label = "Confirm password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = requirePasswordOnLogin,
                    onCheckedChange = { viewModel.setRequirePasswordOnLogin(it) }
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Require my password to log in",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = navigateBack,
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Back"
                    )
                }
                Button(
                    onClick = viewModel::checkAccountDetails,
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Next"
                    )
                }
            }
        }
    }
}