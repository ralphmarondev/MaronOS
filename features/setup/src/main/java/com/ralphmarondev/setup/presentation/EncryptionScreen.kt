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
import androidx.compose.material.icons.outlined.EnhancedEncryption
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.presentation.components.PasswordTextField
import com.ralphmarondev.setup.R
import com.ralphmarondev.setup.SetupViewModel

@Composable
fun EncryptionScreen(
    navigateBack: () -> Unit,
    navigateToLoginDetails: () -> Unit,
    viewModel: SetupViewModel
) {
    val passPhrase = viewModel.passPhrase.collectAsState().value
    val confirmPassPhrase = viewModel.confirmPassPhrase.collectAsState().value
    val response = viewModel.encryptionResponse.collectAsState().value

    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(response) {
        response?.let {
            if (it.success == true) {
                navigateToLoginDetails()
            }
            snackbarState.showSnackbar(
                message = it.message
            )
            viewModel.resetEncryptionResponse()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarState) }
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
                text = stringResource(R.string.passphrase_title),
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(R.string.passphrase_desc),
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(12.dp))
            PasswordTextField(
                value = passPhrase,
                onValueChange = viewModel::onPassPhraseValueChange,
                leadingIcon = Icons.Outlined.EnhancedEncryption,
                label = "Choose a passphrase",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            PasswordTextField(
                value = confirmPassPhrase,
                onValueChange = viewModel::onConfirmPassPhraseValueChange,
                leadingIcon = Icons.Outlined.EnhancedEncryption,
                label = "Confirm the passphrase",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.WarningAmber,
                        contentDescription = "Warning"
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = stringResource(R.string.passphrase_notice_title),
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = stringResource(R.string.passphrase_notice_desc),
                            fontSize = MaterialTheme.typography.labelMedium.fontSize,
                            fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
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
                    onClick = viewModel::checkPassPhrase,
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