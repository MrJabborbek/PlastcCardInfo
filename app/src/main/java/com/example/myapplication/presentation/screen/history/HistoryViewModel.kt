package com.example.myapplication.presentation.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel(){
    private var observeHistoryJob: Job? = null
    private var isInitialized = false

    private val _state = MutableStateFlow(HistoryState())
    val state = _state
        .onStart {
            if (!isInitialized) {
                isInitialized = true
                observeHistory()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HistoryState()
        )

    private fun observeHistory() {
        observeHistoryJob?.cancel()
        _state.update { it.copy(isLoading = true) }
        observeHistoryJob = viewModelScope.launch {

        }
    }

    fun onAction(action: HistoryAction) {
        when (action) {
            HistoryAction.OnClearHistoryClicked -> {}
            is HistoryAction.OnHistoryItemClicked -> {

            }
        }
    }
}