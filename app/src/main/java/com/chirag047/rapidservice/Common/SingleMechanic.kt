package com.chirag047.rapidservice.Common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidservice.Model.MechanicModel
import com.chirag047.rapidservice.R

@Composable
fun SingleMechanic(mechanicModel: MechanicModel, navController: NavController) {
    Row(
        Modifier
            .padding(15.dp, 7.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .padding(15.dp, 0.dp, 7.dp, 0.dp)
                .clip(RoundedCornerShape(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colorScheme.secondary),
            ) {
                Icon(

                    painterResource(id = R.drawable.deliveryboy),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }

        Column(Modifier.weight(1f)) {

            poppinsBoldText(
                contentText = mechanicModel.userName,
                size = 14.sp,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
            )

            Text(
                text = "• " + mechanicModel.mechanicStatus,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 12.sp,
                color = if (mechanicModel.mechanicStatus.equals("Available")) MaterialTheme.colorScheme.primary else if (mechanicModel.mechanicStatus.equals(
                        "On service"
                    )
                ) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
            )
        }

        Spacer(modifier = Modifier.padding(5.dp))

    }
}