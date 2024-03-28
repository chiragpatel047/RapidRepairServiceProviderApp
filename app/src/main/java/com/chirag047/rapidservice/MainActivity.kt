package com.chirag047.rapidservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chirag047.rapidservice.Screens.EnterDetailsScreenOne
import com.chirag047.rapidservice.Screens.ForgetPassword
import com.chirag047.rapidservice.Screens.LoginScreen
import com.chirag047.rapidservice.Screens.SignUpScreen
import com.chirag047.rapidservice.Screens.WelcomeScreen
import com.chirag047.rapidservice.ui.theme.RapidServiceTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RapidServiceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    val auth = Firebase.auth
                    if(auth.currentUser!=null){
                        App("EnterDetailsScreenOne")
                    }else{
                        App("WelcomeScreen")
                    }
                }
            }
        }
    }
}


@Composable
fun App(startScreen : String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startScreen) {
        composable(route = "WelcomeScreen") {
            WelcomeScreen(navController)
        }
        composable(route = "SignUpScreen") {
            SignUpScreen(navController)
        }
        composable(route = "LoginScreen") {
            LoginScreen(navController)
        }
        composable(route = "ForgetPassword") {
            ForgetPassword(navController)
        }
        composable(route = "EnterDetailsScreenOne") {
            EnterDetailsScreenOne(navController)
        }

    }
}