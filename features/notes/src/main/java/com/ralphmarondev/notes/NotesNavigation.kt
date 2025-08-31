package com.ralphmarondev.notes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ralphmarondev.notes.presentation.new_note.NewNoteScreen
import com.ralphmarondev.notes.presentation.note_details.NoteDetailsScreen
import com.ralphmarondev.notes.presentation.note_list.NoteListScreen
import com.ralphmarondev.notes.presentation.update_note.UpdateNoteScreen

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
                },
                navigateToNoteDetails = { noteId ->
                    navController.navigate(Routes.NoteDetails(noteId)) {
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
        composable<Routes.NoteDetails> {
            val id = it.toRoute<Routes.NoteDetails>().id
            NoteDetailsScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToUpdate = {
                    navController.navigate(Routes.UpdateNote(id))
                },
                noteId = id
            )
        }
        composable<Routes.UpdateNote> {
            val id = it.toRoute<Routes.UpdateNote>().id
            UpdateNoteScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                noteId = id
            )
        }
    }
}