package com.chirag047.rapidservice.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.SingleSerivceRequest
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.R

@Composable
fun ServiceRequestListScreen(navController: NavController) {

    val scroll = rememberScrollState()

    Column(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth()) {
            poppinsBoldCenterText(
                contentText = "Service Request list",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(scroll)) {

            //SingleSerivceRequest(R.drawable.car_icon,"Jone snow","TATA Aviniya | Battery",navController)
            //SingleSerivceRequest(R.drawable.motorcycle_icon, "Mukesh patel", "Yamaha R15 | Petrol",navController)
            //SingleSerivceRequest(R.drawable.car_icon, "Tushar gohil", "Maruti Swift | Diesel",navController)
            //SingleSerivceRequest(R.drawable.motorcycle_icon, "Mukesh patel", "Yamaha R15 | Petrol",navController)
            //SingleSerivceRequest(R.drawable.car_icon, "Tushar gohil", "Maruti Swift | Diesel",navController)
            //SingleSerivceRequest(R.drawable.car_icon,"Jone snow","TATA Aviniya | Battery",navController)
            //SingleSerivceRequest(R.drawable.car_icon, "Tushar gohil", "Maruti Swift | Diesel",navController)
            //SingleSerivceRequest(R.drawable.car_icon,"Jone snow","TATA Aviniya | Battery",navController)
            //SingleSerivceRequest(R.drawable.motorcycle_icon, "Mukesh patel", "Yamaha R15 | Petrol",navController)
            //SingleSerivceRequest(R.drawable.car_icon,"Jone snow","TATA Aviniya | Battery",navController)
            //SingleSerivceRequest(R.drawable.motorcycle_icon, "Mukesh patel", "Yamaha R15 | Petrol",navController)
            //SingleSerivceRequest(R.drawable.car_icon, "Tushar gohil", "Maruti Swift | Diesel",navController)

        }

    }
}