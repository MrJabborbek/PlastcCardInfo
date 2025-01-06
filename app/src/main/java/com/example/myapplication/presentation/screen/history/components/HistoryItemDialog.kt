package com.example.myapplication.presentation.screen.history.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.domain.model.CardData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryItemDialog(
    cardData: CardData,
    isVisible: MutableState<Boolean>
) {
    if (isVisible.value){
        BasicAlertDialog(
            modifier = Modifier
                .wrapContentSize()
                .animateContentSize(
                    animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
                )
                .background(color = White, shape = RoundedCornerShape(32.dp)),
            content = {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = cardData.cardNumber,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    )  {
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
            },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = true),
            onDismissRequest = {
                isVisible.value = false
            },
        )
    }
}
