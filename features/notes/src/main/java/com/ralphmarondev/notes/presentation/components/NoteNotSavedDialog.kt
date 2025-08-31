package com.ralphmarondev.notes.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun NoteNotSavedDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Warning",
                color = MaterialTheme.colorScheme.primary
            )
        },
        text = {
            Text(
                text = "Note not saved. Do you want to continue, discarding your changes?",
                color = MaterialTheme.colorScheme.secondary
            )
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "Cancel"
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text = "Continue"
                )
            }
        }
    )
}