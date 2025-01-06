package com.example.myapplication.presentation.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MainScreenRoot(
    viewModel: MainViewModel,
    navigateToHistory: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MainScreen(
        state = state,
        onAction = {
            when(it){
                MainAction.OnHistoryClicked -> navigateToHistory()
                else -> Unit
            }
            viewModel.onAction(it)
        }
    )
}

@Composable
fun MainScreen(
    state: MainState,
    onAction: (MainAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = state.enteredCardNumber,
                onValueChange = { onAction(MainAction.OnCardNumberEntered(it)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                label = { Text("Введите первые 6–8 цифр номера карты", maxLines = 1) }
            )
            Text(
                modifier = Modifier.padding(start = 16.dp).clickable { onAction(MainAction.OnSearchClicked) },
                text = "Поиск",
                fontWeight = FontWeight.Bold,
            )
        }
        Text(
            modifier = Modifier.padding(top = 16.dp).clickable { onAction(MainAction.OnHistoryClicked) },
            text = "История",
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
        state.error?.let {
            Text(
                modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                text = it,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
        state.cardData?.let { cardData ->
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Банк",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "City: ${cardData.bank?.city?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Название: ${cardData.bank?.name?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Схема: ${cardData.scheme?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Тип: ${cardData.type?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Phone: ${cardData.bank?.phone?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Url: ${cardData.bank?.url?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Brand: ${cardData.brand?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding( top = 16.dp),
                    text = "Страна",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Название: ${cardData.country?.name?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Валюта: ${cardData.country?.currency?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Эмодзи: ${cardData.country?.emoji?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Alpha2: ${cardData.country?.alpha2?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Latitude: ${cardData.country?.latitude.takeIf { it != 0 } ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Longitude: ${cardData.country?.longitude.takeIf { it != 0 } ?: "–"}",
                    fontSize = 16.sp
                )

                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Числовой: ${cardData.country?.numeric?.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )

                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Length: ${cardData.number?.length.takeIf { it != 0 } ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Luhn: ${cardData.number?.luhn?.takeIf { it } ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Prepaid: ${cardData.prepaid?.takeIf { it } ?: "–"}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}