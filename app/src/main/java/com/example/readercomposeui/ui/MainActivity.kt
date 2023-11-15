package com.example.readercomposeui.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readercomposeui.ui.navigation.NavGraph
import com.example.readercomposeui.ui.theme.ReaderComposeUITheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         ReaderComposeUITheme {
            TransparentSystemBars()
            ReaderApp()
         }
      }
   }
}

@Composable
fun ReaderApp() {
   Surface(
       modifier = Modifier
           .fillMaxSize(),
       color = MaterialTheme.colorScheme.background
   ) {

      Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
      ) {

         NavGraph()

      }

   }
}

@Composable
private fun TransparentSystemBars() {
   val systemUiController = rememberSystemUiController()
   val useDarkIcons = !isSystemInDarkTheme()

   SideEffect {
      systemUiController.setSystemBarsColor(
          color = Color.Transparent,
          darkIcons = useDarkIcons
      )
   }
}

