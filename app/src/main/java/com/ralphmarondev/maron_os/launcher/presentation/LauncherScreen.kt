package com.ralphmarondev.maron_os.launcher.presentation

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.maron_os.R
import com.ralphmarondev.maron_os.launcher.presentation.components.BottomNavigationBar
import com.ralphmarondev.maron_os.launcher.presentation.screens.FirstScreen
import com.ralphmarondev.maron_os.launcher.presentation.screens.SecondScreen
import com.ralphmarondev.presentation.theme.LocalThemeState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LauncherScreen(
    navController: NavHostController
) {
    val viewModel: LauncherViewModel = koinViewModel()
    val themeState = LocalThemeState.current
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insetsController = window?.let {
                WindowCompat.getInsetsController(it, view)
            }
            insetsController?.isAppearanceLightStatusBars = themeState.darkTheme.value
        }
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.wallpaper),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val pagerState = rememberPagerState { 2 }
            HorizontalPager(
                state = pagerState
            ) { currentPage ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    when (currentPage) {
                        0 -> FirstScreen(
                            navController = navController,
                            viewModel = viewModel
                        )

                        else -> SecondScreen(
                            navController = navController
                        )
                    }
                }
            }
            BottomNavigationBar(
                pagerState = pagerState,
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}
