package com.ralphmarondev.notes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.notes.presentation.new_note.NewNoteScreen
import com.ralphmarondev.notes.presentation.note_list.NoteListScreen

@Composable
fun NotesNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.NoteList
    ) {
        composable<Routes.NoteList> {
            NoteListScreen(
                navigateToNewNote = {
                    navController.navigate(Routes.NewNote) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.NewNote> {
            NewNoteScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}