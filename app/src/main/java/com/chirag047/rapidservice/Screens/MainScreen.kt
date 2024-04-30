package com.chirag047.rapidservice.Screens

import android.Manifest
import android.app.Activity
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chirag047.rapidservice.Common.NavigationItem
import com.chirag047.rapidservice.R

@Composable
fun MainScreen(navController: NavController, sharedPreferences: SharedPreferences) {
    val list = listOf(
        NavigationItem.HomeNav,
        NavigationItem.TrackNav,
        NavigationItem.MechanicNav,
        NavigationItem.ProfileNav
    )

    val bottomNavController = rememberNavController()
    sharedPreferences.edit()
        .putBoolean("isDataFilled", true).apply()

    val activity = LocalContext.current as Activity

    val notificationPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
            it.entries.forEach {
                if (it.value) {

                }
            }
        })


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            LaunchedEffect(key1 = Unit) {
                notificationPermission.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
            }
        }
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            navApp(bottomNavController, navController, sharedPreferences)
        }
        bottomNavigationCustom(bottomNavController, list = list)
    }

}


@Composable
fun bottomNavigationCustom(navController: NavController, list: List<NavigationItem>) {

    var currentNavScreen = remember { mutableStateOf(NavigationItem.HomeNav.route) }

    navController.addOnDestinationChangedListener(object :
        NavController.OnDestinationChangedListener {
        override fun onDestinationChanged(
            controller: NavController,
            destination: NavDestination,
            arguments: Bundle?
        ) {
            currentNavScreen.value = destination.route!!
        }
    })

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 15.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        list.forEach {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    if (!currentNavScreen.value.equals(it.route)) {
                        navController.navigate(it.route) {
                            launchSingleTop = true
                            popUpTo(it.route)
                        }
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = it.icon),
                    tint = if (currentNavScreen.value.equals(it.route)) MaterialTheme.colorScheme.primary else Color.LightGray,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = it.label,
                    color = if (currentNavScreen.value.equals(it.route)) MaterialTheme.colorScheme.primary else Color.LightGray,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            }
        }
    }
}

@Composable
fun navApp(
    bottomNavController: NavHostController,
    navController: NavController,
    sharedPreferences: SharedPreferences
) {

    NavHost(navController = bottomNavController, startDestination = "HomeScreen") {
        composable(route = "HomeScreen") {
            HomeScreen(navController, sharedPreferences)
        }
        composable(route = "TrackScreen") {
            TrackScreen(navController, sharedPreferences)
        }
        composable(route = "MechanicScreen") {
            MechanicScreen(navController, sharedPreferences)
        }
        composable(route = "ProfileScreen") {
            ProfileScreen(navController, sharedPreferences)
        }
    }
}