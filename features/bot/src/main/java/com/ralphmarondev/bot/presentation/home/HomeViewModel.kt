package com.ralphmarondev.bot.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = "api_key_goes_here_hehe"
    )

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()


    fun onMessageValueChange(value: String) {
        _message.value = value
    }

    fun send() {
        viewModelScope.launch {
            val chat = generativeModel.startChat()
            val response = chat.sendMessage(_message.value)
            Log.d("App", "Response from gemini: ${response.text.toString()}")
        }
    }
}