package com.example.readercomposeui.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readercomposeui.ui.theme.md_theme_dark_onTertiary

/**
 * @author : Mingaleev D
 * @data : 15.11.2023
 */

@Composable
fun TitleSelection(
    modifier: Modifier = Modifier,
    label: String
) {

   Surface(
       modifier = modifier.padding(start = 5.dp, top = 2.dp)
   ) {
      Column {
         Text(
             text = label,
             fontSize = 19.sp,
             fontWeight = FontWeight.Bold,
             textAlign = TextAlign.Left,
             color = md_theme_dark_onTertiary
         )
      }
   }
}