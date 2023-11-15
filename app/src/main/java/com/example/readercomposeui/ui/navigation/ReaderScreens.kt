package com.example.readercomposeui.ui.navigation

/**
 * @author : Mingaleev D
 * @data : 14.11.2023
 */

enum class ReaderScreens {

   SplashScreen,
   LoginScreen,
   CreateAccountScreen,
   ReaderHomeScreen,
   SearchScreen,
   DetailsScreen,
   StatsScreen,
   UpdateScreen;

   companion object {

      fun fromRoute(route: String): ReaderScreens {
         return when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            CreateAccountScreen.name -> CreateAccountScreen
            ReaderHomeScreen.name -> ReaderHomeScreen
            SearchScreen.name -> SearchScreen
            DetailsScreen.name -> DetailsScreen
            StatsScreen.name -> StatsScreen
            UpdateScreen.name -> UpdateScreen
            null -> ReaderHomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized.")
         }
      }
   }
}