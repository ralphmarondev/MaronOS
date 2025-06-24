package com.ralphmarondev.bot.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.ralphmarondev.bot.domain.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = ""
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
            try {
                val chat = generativeModel.startChat(
                    history = _conversation.value.map {
                        content(if (it.role == "kate") "model" else it.role) { text(it.message) }
                    }.toList()
                )

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
            } catch (e: Exception) {
                _conversation.value += Message(
                    message = "Sorry, I don't understand you.",
                    role = "kate"
                )
                Log.e("App", "Error: ${e.message}")
            }
            _message.value = ""
        }
    }
}