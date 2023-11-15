package com.example.readercomposeui.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.readercomposeui.ui.screens.home.HomeScreen
import com.example.readercomposeui.ui.screens.login.LoginScreen
import com.example.readercomposeui.ui.screens.splash.ReaderSplash

/**
 * @author : Mingaleev D
 * @data : 14.11.2023
 */
@Composable
fun NavGraph() {

   val navController = rememberNavController()
   NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name) {
      composable(route = ReaderScreens.SplashScreen.name) {
         ReaderSplash(navController = navController)
      }
      composable(route = ReaderScreens.LoginScreen.name) {
         LoginScreen(navController = navController)
      }
      composable(route = ReaderScreens.ReaderHomeScreen.name) {
         HomeScreen(navController = navController)
      }
   }

}