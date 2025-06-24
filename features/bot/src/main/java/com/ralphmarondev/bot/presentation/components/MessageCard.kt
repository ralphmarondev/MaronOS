package com.ralphmarondev.bot.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ralphmarondev.bot.domain.model.Message

@Composable
fun MessageCard(
    message: Message
) {
    val isKate = message.role == "kate"
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .align(
                        if (isKate) Alignment.BottomStart else Alignment.BottomEnd
                    )
                    .padding(
                        start = if (isKate) 8.dp else 78.dp,
                        end = if (isKate) 70.dp else 8.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (isKate) MaterialTheme.colorScheme.secondaryContainer
                        else MaterialTheme.colorScheme.tertiaryContainer
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = message.message,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isKate) MaterialTheme.colorScheme.onSecondaryContainer
                    else MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}