package com.example.readercomposeui.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * @author : Mingaleev D
 * @data : 16.11.2023
 */

@Composable fun BookRating(score: Double = 4.5) {
   Surface(
       modifier = Modifier
           .height(70.dp)
           .padding(4.dp),
       shape = RoundedCornerShape(56.dp),
       shadowElevation = 6.dp
   ) {
      Column(
          modifier = Modifier.padding(4.dp),
          horizontalAlignment = Alignment.CenterHorizontally
      ) {
         Icon(
             imageVector = Icons.Filled.StarBorder,
             contentDescription = null,
             modifier = Modifier.padding(3.dp)
         )

         Text(text = score.toString(), style = MaterialTheme.typography.titleMedium)
      }
   }
}