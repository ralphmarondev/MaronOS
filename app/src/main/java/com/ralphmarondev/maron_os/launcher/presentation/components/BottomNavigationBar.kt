package com.ralphmarondev.maron_os.launcher.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ralphmarondev.maron_os.R
import com.ralphmarondev.maron_os.launcher.domain.model.AppData

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    navController: NavHostController
) {
    val bottomIcons = listOf(
        AppData(
            iconRes = R.drawable.notepad,
            name = "Notepad",
            onClick = {}
        ),
        AppData(
            iconRes = R.drawable.browser,
            name = "Browser",
            onClick = {}
        ),
        AppData(
            iconRes = R.drawable.clock,
            name = "Clock",
            onClick = {}
        ),
        AppData(
            iconRes = R.drawable.setting,
            name = "Settings",
            onClick = {}
        )
    )

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) {
                val color = if (pagerState.currentPage == it) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.onPrimaryContainer
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(color)
                        .padding(4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceAround,
            userScrollEnabled = false,
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(bottomIcons) { app ->
                AppWithIconContainer(
                    image = app.iconRes,
                    onClick = app.onClick
                )
            }
        }
    }
}