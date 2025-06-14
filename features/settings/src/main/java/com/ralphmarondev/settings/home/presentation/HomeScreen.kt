package com.ralphmarondev.settings.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MiscellaneousServices
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.SettingsApplications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.settings.R
import com.ralphmarondev.settings.home.presentation.components.SettingCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val options = listOf(
        SettingOption(
            leadingIcon = Icons.Outlined.MiscellaneousServices,
            title = "General",
            description = "Language and input settings",
            onClick = {}
        ),
        SettingOption(
            leadingIcon = Icons.Outlined.MiscellaneousServices,
            title = "Backup and Sync",
            description = "Data backup and synchronization",
            onClick = {}
        ),
        SettingOption(
            leadingIcon = Icons.Outlined.Notifications,
            title = "Notifications",
            description = "Block, Allow, Priorities",
            onClick = {}
        ),
        SettingOption(
            leadingIcon = Icons.Outlined.SettingsApplications,
            title = "Permissions",
            description = "Application permissions",
            onClick = {}
        ),
        SettingOption(
            leadingIcon = Icons.Outlined.Info,
            title = "About",
            description = "Know about your Maron OS",
            onClick = {}
        ),
        SettingOption(
            leadingIcon = Icons.AutoMirrored.Outlined.Logout,
            title = "Logout",
            description = "Logout from Maron OS",
            onClick = {}
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(R.drawable.ralphmaron),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Ralph Maron Eda",
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "ralphmaron",
                                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }
            }
            items(options) {
                SettingCard(
                    leadingIcon = it.leadingIcon,
                    title = it.title,
                    description = it.description,
                    onClick = it.onClick,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )
            }
            item { Spacer(modifier = Modifier.height(100.dp)) }
        }
    }
}

data class SettingOption(
    val leadingIcon: ImageVector,
    val title: String,
    val description: String,
    val onClick: () -> Unit
)