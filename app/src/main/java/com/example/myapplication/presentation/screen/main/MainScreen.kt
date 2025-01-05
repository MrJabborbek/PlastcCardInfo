package com.example.myapplication.presentation.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreenRoot(
    viewModel: MainViewModel
) {
    val state by viewModel.state.collectAsState()
    MainScreen(
        state = state,
        onAction = viewModel::onAction
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
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.enteredCardNumber,
            onValueChange = { onAction(MainAction.OnCardNumberEntered(it)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                label = { Text("Введите первые 6–8 цифр номера карты") }
        )
        state.cardData?.let { cardData ->
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Банк",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "City: ${cardData.bank.city.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Name: ${cardData.bank.name.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Phone: ${cardData.bank.phone.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp //TODO
                )
                Text(
                    text = "Url: ${cardData.bank.url.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp //TODO
                )

                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = "Brand: ${cardData.brand.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = "Country",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Alpha2: ${cardData.country.alpha2.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Currency: ${cardData.country.currency.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Emoji: ${cardData.country.emoji.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Latitude: ${cardData.country.latitude.takeIf { it != 0 } ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Longitude: ${cardData.country.longitude.takeIf { it != 0 } ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Name: ${cardData.country.name.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Numeric: ${cardData.country.numeric.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = "Number",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Length: ${cardData.number.length.takeIf { it != 0 } ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Luhn: ${cardData.number.luhn.takeIf { it } ?: "–"}",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = "Prepaid: ${cardData.prepaid.takeIf { it } ?: "–"}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = "Scheme: ${cardData.scheme.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = "Type: ${cardData.type.takeIf { it.isNotBlank()} ?: "–"}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}