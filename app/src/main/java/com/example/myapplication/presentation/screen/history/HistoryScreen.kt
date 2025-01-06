package com.example.myapplication.presentation.screen.history

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.domain.model.CardData
import com.example.myapplication.presentation.TopBar
import com.example.myapplication.presentation.screen.history.components.HistoryItemDialog

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

@SuppressLint("RememberReturnType")
@Composable
fun HistoryScreen(
    state: HistoryState,
    onAction: (HistoryAction) -> Unit,
) {
    var cardData by remember { mutableStateOf<CardData?>(null) }
    val isDialogVisible = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(
            isBackButtonVisible = true,
            text = "История запросов",
            onBackButtonClicked = {
                onAction(HistoryAction.OnBackClicked)
            },
            isTextButtonVisible = true,
            textButton = "Очистить",
            onTextButtonClicked = {
                onAction(HistoryAction.OnClearHistoryClicked)
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            state.history.forEach {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp).clickable {
                        isDialogVisible.value = true
                        cardData = it
                        onAction(HistoryAction.OnHistoryItemClicked(it))
                    },
                    text = it.cardNumber,
                    fontSize = 24.sp,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue
                )
            }
//            items(items = state.history) { cardData ->
//                Text(text = cardData.cardNumber)
//            }
        }
    }
    cardData?.let {
        HistoryItemDialog(
            cardData = it,
            isVisible = isDialogVisible
        )
    }
}

