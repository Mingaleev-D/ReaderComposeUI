package com.example.readercomposeui.ui.screens.splash

import android.content.res.Configuration
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readercomposeui.ui.components.ReaderLogoText
import com.example.readercomposeui.ui.navigation.ReaderScreens
import com.example.readercomposeui.ui.theme.ReaderComposeUITheme
import com.example.readercomposeui.ui.theme.md_theme_light_shadow
import com.example.readercomposeui.ui.theme.seed
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

/**
 * @author : Mingaleev D
 * @data : 14.11.2023
 */

@Composable
fun ReaderSplash(navController: NavController) {

   val scale = remember { Animatable(0f) }

   LaunchedEffect(key1 = true) {
      scale.animateTo(
          targetValue = 0.9f,
          animationSpec = tween(
              durationMillis = 800,
              easing = { OvershootInterpolator(3.1f).getInterpolation(it) }),
      )
      delay(2000L)

//      if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
//         navController.navigate(route = ReaderScreens.LoginScreen.name)
//      }else{
//         navController.navigate(route = ReaderScreens.ReaderHomeScreen.name)
//      }

      navController.navigate(route = ReaderScreens.ReaderHomeScreen.name)
   }

   Surface(
       modifier = Modifier
           .padding(15.dp)
           .size(330.dp)
           .scale(scale.value),
       shape = CircleShape,
       color = Color.Transparent,
       border = BorderStroke(3.dp, color = seed),
   ) {

      Column(
          modifier = Modifier.padding(1.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
      ) {

         ReaderLogoText()

         Spacer(modifier = Modifier.size(15.dp))
         Text(
             text = "Читай. Измени себя \uD83D\uDE09",
             style = MaterialTheme.typography.headlineSmall.copy(
                 fontWeight = FontWeight.Bold,
                 color = md_theme_light_shadow,
                 fontSize = 20.sp
             )
         )

      }
   }
}

@Preview(
    name = "Ночь-Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "День-Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun ReaderSplashPreview() {
   ReaderComposeUITheme {
      ReaderSplash(navController = NavController(context = LocalContext.current))
   }

}