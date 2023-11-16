package com.example.readercomposeui.ui.components.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

/**
 * @author : Mingaleev D
 * @data : 15.11.2023
 */

@Composable fun FABContent(onTab: () -> Unit) {
   FloatingActionButton(
       onClick = { onTab() },
       shape = RoundedCornerShape(50.dp),
       containerColor = MaterialTheme.colorScheme.primary
   ) {
      Icon(imageVector = Icons.Default.Add, contentDescription = null)
   }
}