package com.example.studybuddy.Login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studybuddy.ui.Presentation.Dashboard.DashboardScreenRoute


@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, viewmodel:LoginViewModel){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login" , builder = {
        composable("login"){
            LoginScreen(modifier,navController,viewmodel)
        }
        composable("SinUp"){
            SinUpScreen(modifier,navController,viewmodel)
        }
//        composable("dashboard") {  // Add the dashboard screen route
//           DashboardScreenRoute(navigator = navController)  // You can pass data as needed
//        }
        composable("forgot") {
            ForgotPassword(modifier,navController,viewmodel )
        }
    })

}