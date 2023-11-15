package com.example.readercomposeui.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * @author : Mingaleev D
 * @data : 14.11.2023
 */

@Composable
fun ReaderLogoText(
    modifier: Modifier = Modifier,
) {
   Text(
       text = "Приложение читателя",
       modifier = modifier,
       style = MaterialTheme.typography.displayLarge.copy(
           color = Color.Red,
           fontWeight = FontWeight.Bold,
           fontSize = 24.sp
       )
   )
}