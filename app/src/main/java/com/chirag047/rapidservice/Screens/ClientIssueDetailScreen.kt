package com.chirag047.rapidservice.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.ActionBarWIthBack
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.Common.poppinsText

@Composable
fun ClientIssueDetailScreen(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState()
        Column(Modifier.fillMaxWidth()) {
            ActionBarWIthBack(title = "Client Issue details")

            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(scroll)
            ) {
                poppinsBoldText(
                    contentText = "Client Details",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 5.dp, 15.dp, 0.dp)
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                ) {

                    Row(
                        modifier = Modifier.padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column() {
                            detailTitle(title = "Vehicle owner")
                            detailTitle(title = "Vehicle Type")
                            detailTitle(title = "Vehicle Company")
                            detailTitle(title = "Vehicle Model")
                            detailTitle(title = "Fuel Type")
                            detailTitle(title = "License Plate")
                        }
                        Column() {
                            colanText()
                            colanText()
                            colanText()
                            colanText()
                            colanText()
                            colanText()
                        }
                        Column {
                            detailContent("Ankit Patel")
                            detailContent("Car")
                            detailContent("TATA")
                            detailContent("Aviniya")
                            detailContent("Battery")
                            detailContent("GJ 05 SC 3006")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun detailTitle(title: String) {
    poppinsBoldText(
        contentText = title,
        size = 14.sp,
        modifier = Modifier
            .padding(15.dp, 5.dp, 15.dp, 0.dp)
    )
}

@Composable
fun colanText() {
    poppinsBoldText(
        contentText = ":",
        size = 14.sp,
        modifier = Modifier
            .padding(15.dp, 5.dp, 15.dp, 0.dp)
    )
}

@Composable
fun detailContent(content: String) {
    poppinsText(
        contentText = content,
        size = 14.sp,
        modifier = Modifier
            .padding(15.dp, 5.dp, 15.dp, 0.dp)
    )
}