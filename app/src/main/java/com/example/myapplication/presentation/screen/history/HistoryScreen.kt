package com.example.myapplication.presentation.screen.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.presentation.TopBar

@Composable
fun HistoryScreenRoot(
    viewModel: HistoryViewModel,
    navigateBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HistoryScreen(
        state = state,
        onAction = {
            when(it){
                is HistoryAction.OnBackClicked -> navigateBack()
                else -> Unit
            }
            viewModel.onAction(it)
        },
    )
}

@Composable
fun HistoryScreen(
    state: HistoryState,
    onAction: (HistoryAction) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(
            isBackButtonVisible = true,
            text = "История запросов",
            onBackButtonClicked = {
                onAction(HistoryAction.OnBackClicked)
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            items(items = state.history) { cardData ->
//                Text(text = cardData.cardNumber)
            }
        }

    }
}