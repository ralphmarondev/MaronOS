package com.ralphmarondev.maron_os.launcher.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ralphmarondev.maron_os.R
import com.ralphmarondev.maron_os.launcher.domain.model.AppData
import com.ralphmarondev.maron_os.launcher.presentation.LauncherViewModel
import com.ralphmarondev.maron_os.launcher.presentation.components.AppWithIconAndNameContainer
import com.ralphmarondev.maron_os.launcher.presentation.components.DateTimeWidget
import com.ralphmarondev.maron_os.navigation.Routes

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: LauncherViewModel
) {
    val appList = listOf(
        AppData(
            iconRes = R.drawable.camera,
            name = "Camera",
            onClick = {
                navController.navigate(Routes.Camera) {
                    launchSingleTop = true
                }
            }
        ),
        AppData(
            iconRes = R.drawable.calendar,
            name = "Calendar",
            onClick = {}
        ),
        AppData(
            iconRes = R.drawable.gallery,
            name = "Gallery",
            onClick = {
                navController.navigate(Routes.Gallery) {
                    launchSingleTop = true
                }
            }
        )
    )

    Column(
        modifier = modifier
    ) {
        DateTimeWidget(
            viewModel = viewModel,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            userScrollEnabled = false
        ) {
            items(appList) { app ->
                AppWithIconAndNameContainer(
                    image = app.iconRes,
                    appName = app.name,
                    onClick = app.onClick
                )
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}