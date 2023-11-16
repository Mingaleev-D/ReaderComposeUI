package com.example.readercomposeui.ui.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readercomposeui.ui.theme.seed

/**
 * @author : Mingaleev D
 * @data : 16.11.2023
 */

@Preview
@Composable
fun RoundedButton(
    label: String = "Рейтинг",
    radius: Int = 29,
    onPress: () -> Unit = {}
) {

   Surface(
       modifier = Modifier.clip(
           RoundedCornerShape(
               bottomEndPercent = radius,
               topStartPercent = radius
           )
       ),
       color = seed
   ) {
      Column(
          modifier = Modifier
              .width(90.dp)
              .heightIn(40.dp)
              .clickable { onPress() },
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
      ) {
         Text(
             text = label,
             style = TextStyle(color = Color.White, fontSize = 15.sp)
         )
      }

   }
}