package com.example.myapplication.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private var fetchDataJob: Job? = null
    private var isInitialized = false
    private val _state = MutableStateFlow(MainState())
    val state = _state
        .onStart {
            if (!isInitialized) {
                isInitialized = true
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: MainAction) {
        when (action) {
            is MainAction.OnCardNumberEntered -> {
                if (action.value.toLongOrNull() == null && action.value.isNotEmpty()){
                    return
                }
                if (action.value.length > 4) {
                    return
                }
                _state.update { it.copy(enteredCardNumber = action.value)}
                if (action.value.length == 4) {
                    getCardData(action.value)
                }
            }
        }
    }

    private fun getCardData(cardNumber: String) {
        fetchDataJob?.cancel()
        _state.update { it.copy(isLoading = true) }
        fetchDataJob = viewModelScope.launch {

        }

    }
}