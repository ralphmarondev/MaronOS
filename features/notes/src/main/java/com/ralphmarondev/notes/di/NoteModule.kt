package com.ralphmarondev.notes.di

import com.ralphmarondev.notes.presentation.new_note.NewNoteViewModel
import com.ralphmarondev.notes.presentation.note_list.NoteListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val noteModule = module {
    viewModelOf(::NoteListViewModel)
    viewModelOf(::NewNoteViewModel)
}