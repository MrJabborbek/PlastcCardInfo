package com.example.myapplication.presentation.screen.main

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.repository.CardInfoRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val cardInfoRepository: CardInfoRepository,
    private val snackbarHostState: SnackbarHostState
): ViewModel() {
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
                if (action.value.length > 8) {
                    return
                }
                _state.update { it.copy(enteredCardNumber = action.value)}
                if (action.value.length == 8) {
                    getCardData(action.value)
                }
            }

            MainAction.OnHistoryClicked -> {}
        }
    }

    private fun getCardData(cardNumber: String) {
        fetchDataJob?.cancel()
        _state.update { it.copy(isLoading = true) }
        fetchDataJob = viewModelScope.launch {
            cardInfoRepository.getInfoByCard(cardNumber)?.let { cardData ->
                println("CardData: 2$cardData")
                _state.update { it.copy(
                    isLoading = false,
                    cardData = cardData
                ) }
            } ?: kotlin.run {
                showSnackbar("Data not found")
                _state.update { it.copy(
                        isLoading = false,
                        cardData = null
                    )
                }
            }
        }
    }

    private fun showSnackbar(message: String) {
        viewModelScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }
}