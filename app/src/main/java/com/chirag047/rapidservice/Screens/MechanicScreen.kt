package com.chirag047.rapidservice.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.ManageSingleMechanic
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.R

@Composable
fun MechanicScreen(navController: NavController){
    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState()

        Column(Modifier.fillMaxWidth()) {
            Column(Modifier.fillMaxWidth()) {
                poppinsBoldCenterText(
                    contentText = "Mechanics",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(scroll)
            ) {
                ManageSingleMechanic("Apurva Gandhi", "Available")
                ManageSingleMechanic("Chintan Gajjar", "Currently on service")
                ManageSingleMechanic("Papesh Padhare", "Not available")
            }
        }
        ExtendedFloatingActionButton(
            text = {
                Text(
                    text = "New Mechanic",
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            },
            onClick = {
                navController.navigate("")
            },
            icon = { Icon(Icons.Filled.Add, "") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp, 20.dp)
        )

    }
}