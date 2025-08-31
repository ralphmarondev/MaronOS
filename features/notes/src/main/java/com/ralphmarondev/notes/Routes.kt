package com.ralphmarondev.notes

import kotlinx.serialization.Serializable

@Serializable
object Routes {

    @Serializable
    data object NoteList

    @Serializable
    data object NewNote

    @Serializable
    data class NoteDetails(val id: Long)

    @Serializable
    data class UpdateNote(val id: Long)
}