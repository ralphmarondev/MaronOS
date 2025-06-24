package com.ralphmarondev.bot.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()


    fun onMessageValueChange(value: String) {
        _message.value = value
    }

    fun send() {

    }
}