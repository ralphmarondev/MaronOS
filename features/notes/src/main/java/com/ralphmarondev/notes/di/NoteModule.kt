package com.ralphmarondev.notes.di

import com.ralphmarondev.notes.data.repository.NoteRepositoryImpl
import com.ralphmarondev.notes.domain.repository.NoteRepository
import com.ralphmarondev.notes.domain.usecase.CreateNoteUseCase
import com.ralphmarondev.notes.domain.usecase.DeleteNoteUseCase
import com.ralphmarondev.notes.domain.usecase.GetAllNotesUseCase
import com.ralphmarondev.notes.domain.usecase.GetNoteByIdUseCase
import com.ralphmarondev.notes.domain.usecase.UpdateNoteUseCase
import com.ralphmarondev.notes.presentation.new_note.NewNoteViewModel
import com.ralphmarondev.notes.presentation.note_list.NoteListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val noteModule = module {
    singleOf(::NoteRepositoryImpl).bind<NoteRepository>()

    factoryOf(::CreateNoteUseCase)
    factoryOf(::DeleteNoteUseCase)
    factoryOf(::GetAllNotesUseCase)
    factoryOf(::GetNoteByIdUseCase)
    factoryOf(::UpdateNoteUseCase)

    viewModelOf(::NoteListViewModel)
    viewModelOf(::NewNoteViewModel)
}