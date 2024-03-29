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
import com.chirag047.rapidservice.Common.TrackHistorySingle
import com.chirag047.rapidservice.Common.poppinsBoldCenterText

@Composable
fun ServiceHistoryScreen(navController: NavController) {

    val scroll = rememberScrollState()

    Column(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth()) {
            poppinsBoldCenterText(
                contentText = "Service History",
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
                "Mukesh patel", "Auto Rickshaw | CNG",
                "Mon 9 Feb 2024 | 11.47 AM"
            )

        }
    }
}