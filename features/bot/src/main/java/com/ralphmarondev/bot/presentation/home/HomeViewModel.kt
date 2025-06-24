package com.ralphmarondev.bot.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.ralphmarondev.bot.domain.model.Message
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

    private val _conversation = MutableStateFlow<List<Message>>(emptyList())
    val conversation = _conversation.asStateFlow()


    fun onMessageValueChange(value: String) {
        _message.value = value
    }

    fun send() {
        viewModelScope.launch {
            val chat = generativeModel.startChat()

            _conversation.value += Message(
                message = _message.value.trim(),
                role = "user"
            )

            val response = chat.sendMessage(_message.value)
            _conversation.value += Message(
                message = response.text.toString(),
                role = "kate"
            )
            Log.d("App", "Response: ${response.text.toString()}")
        }
    }
}