package com.ralphmarondev.maron_os.launcher.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun AppWithIconContainer(
    image: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            onClick = onClick,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(image),
                    contentDescription = "App Icon",
                    modifier = Modifier
                        .size(36.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}