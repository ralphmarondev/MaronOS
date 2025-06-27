package com.ralphmarondev.bot.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.ralphmarondev.bot.domain.model.Message
import com.ralphmarondev.bot.domain.usecase.CreateMessageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val generativeModel: GenerativeModel,
    private val createMessageUseCase: CreateMessageUseCase
) : ViewModel() {

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
                _conversation.value += Message(
                    message = _message.value.trim(),
                    role = "user"
                )
                _message.value = ""

                val placeHolderIndex = _conversation.value.size
                _conversation.value += Message(
                    message = "Typing...",
                    role = "kate"
                )

                val chat = generativeModel.startChat(
                    history = _conversation.value.dropLast(1).map {
                        content(if (it.role == "kate") "model" else it.role) { text(it.message) }
                    }.toList()
                )

                val response = chat.sendMessage(_message.value.trim())
                _conversation.value = _conversation.value.toMutableList().apply {
                    this[placeHolderIndex] = Message(
                        message = response.text.toString(),
                        role = "kate"
                    )
                }
            } catch (e: Exception) {
                Log.e("App", "Error: ${e.message}")
                _conversation.value = _conversation.value.toMutableList().apply {
                    this[this.lastIndex] = Message(
                        message = "Sorry, I'm too cute to answer you.",
                        role = "kate"
                    )
                }
            }
        }
    }
}