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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ralphmarondev.maron_os.R
import com.ralphmarondev.maron_os.launcher.domain.model.AppData
import com.ralphmarondev.maron_os.launcher.presentation.components.AppWithIconAndNameContainer

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val appList = listOf(
        AppData(
            iconRes = R.drawable.weather,
            name = "Weather",
            onClick = {}
        ),
        AppData(
            iconRes = R.drawable.calculator,
            name = "Calculator",
            onClick = {}
        )
    )

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.about_app),
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.End,
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