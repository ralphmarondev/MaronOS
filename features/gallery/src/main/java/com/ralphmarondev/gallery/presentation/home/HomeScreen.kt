package com.ralphmarondev.gallery.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val images = viewModel.images.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Gallery"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = if (images.isEmpty()) {
                GridCells.Fixed(1)
            } else {
                GridCells.Fixed(3)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = if (images.isEmpty()) {
                Arrangement.Center
            } else {
                Arrangement.spacedBy(4.dp)
            },
            verticalArrangement = if (images.isEmpty()) {
                Arrangement.Center
            } else {
                Arrangement.spacedBy(4.dp)
            }
        ) {
            items(images) { file ->
                AsyncImage(
                    model = file,
                    contentDescription = file.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                AnimatedVisibility(images.isEmpty()) {
                    Text(
                        text = "Gallery is empty!",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}