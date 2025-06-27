package com.ralphmarondev.bot.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.bot.domain.model.Message
import com.ralphmarondev.bot.domain.usecase.CreateMessageUseCase
import com.ralphmarondev.bot.domain.usecase.GenerateResponseUseCase
import com.ralphmarondev.bot.domain.usecase.GetAllMessageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val createMessageUseCase: CreateMessageUseCase,
    private val getAllMessageUseCase: GetAllMessageUseCase,
    private val generateResponseUseCase: GenerateResponseUseCase
) : ViewModel() {

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    private val _conversation = MutableStateFlow<List<Message>>(emptyList())
    val conversation = _conversation.asStateFlow()


    init {
        viewModelScope.launch {
            _conversation.value = emptyList()
            getAllMessageUseCase().collect {
                _conversation.value = it
            }
        }
    }

    fun onMessageValueChange(value: String) {
        _message.value = value
    }

    fun send() {
        viewModelScope.launch {
            try {
                val userMessage = Message(
                    message = _message.value.trim(),
                    role = "user"
                )
                _conversation.value += userMessage
                createMessageUseCase(message = userMessage)
                _message.value = ""

                val placeHolderIndex = _conversation.value.size
                _conversation.value += Message(
                    message = "Typing...",
                    role = "kate"
                )

                _conversation.value = _conversation.value.toMutableList().apply {
                    val botResponse = Message(
                        message = generateResponseUseCase(
                            _message.value.trim(),
                            _conversation.value.dropLast(1)
                        ),
                        role = "kate"
                    )
                    this[placeHolderIndex] = botResponse
                    createMessageUseCase(botResponse)
                }
            } catch (e: Exception) {
                Log.e("App", "Error: ${e.message}")
                _conversation.value = _conversation.value.toMutableList().apply {
                    val botResponse = Message(
                        message = "Sorry, I'm too cute to answer you.",
                        role = "kate"
                    )
                    this[this.lastIndex] = botResponse
                    createMessageUseCase(botResponse)
                }
            }
        }
    }
}