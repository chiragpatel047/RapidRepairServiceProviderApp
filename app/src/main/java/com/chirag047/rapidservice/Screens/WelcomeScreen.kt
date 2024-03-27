package com.chirag047.rapidservice.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.FullWidthButton
import com.chirag047.rapidservice.Common.SubjectImage
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Common.poppinsCenterText
import com.chirag047.rapidservice.R

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.padding(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            SubjectImage(
                image = R.drawable.hello_subject_image,
                Modifier.padding(40.dp, 0.dp)
            )
        }

        poppinsBoldCenterText(
            contentText = "Hello, Welcome dear!!",
            size = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 10.dp, 30.dp, 10.dp)
        )

        poppinsCenterText(
            contentText = "Get customers just at your fingers, expand your services!!",
            size = 14.sp,
            modifier = Modifier.padding(20.dp, 5.dp)
        )

        Spacer(modifier = Modifier.padding(20.dp))

        FullWidthButton(label = "Let's Get Start", color = MaterialTheme.colorScheme.primary) {
            navController.navigate("OnBoardingScreen")
        }
        Spacer(modifier = Modifier.padding(40.dp))
    }
}