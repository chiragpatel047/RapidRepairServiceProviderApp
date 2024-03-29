package com.chirag047.rapidservice.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.TrackHistorySingle
import com.chirag047.rapidservice.Common.TrackSingle
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.Common.textWithSeeAllText

@Composable
fun TrackScreen(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth()) {
            poppinsBoldCenterText(
                contentText = "Track",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            val scroll = rememberScrollState()

            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(scroll)
            ) {
                Spacer(modifier = Modifier.padding(4.dp))
                poppinsBoldText(
                    contentText = "Currently live services",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 5.dp, 15.dp, 0.dp)
                )
                
                Spacer(modifier = Modifier.padding(2.dp))

                TrackSingle("Ashish Kharvar", "Mahindra Thar | Diesel") {
                    navController.navigate("TrackNowScreen")
                }

                Spacer(modifier = Modifier.padding(6.dp))

                textWithSeeAllText(title = "Service History") {
                    navController.navigate("ServiceHistoryScreen")
                }

                Spacer(modifier = Modifier.padding(6.dp))
                TrackHistorySingle(
                    "Mukesh patel", "Auto Rickshaw | CNG",
                    "Mon 9 Feb 2024 | 11.47 AM"
                )
                TrackHistorySingle(
                    "Ramesh Sutariya",
                    "Yamaha Duke | Petrol ",
                    "Wed 15 Jan 2024 | 01.35 PM"
                )
                TrackHistorySingle(
                    "Ankit Kharawar",
                    "TATA Aviniya | Battery",
                    "Tue 30 Nov 2023 | 6.50 PM"
                )
                TrackHistorySingle(
                    "Ashish Kharawar", "Mahindra Thar | Diesel",
                    "Sun 6 Aug 2023 | 07.30 PM"
                )
            }

        }
    }
}