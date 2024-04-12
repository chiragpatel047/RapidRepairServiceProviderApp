package com.chirag047.rapidservice.Common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidservice.Model.OrderModel
import com.chirag047.rapidservice.R

@Composable
fun SingleSerivceRequest(orderModel: OrderModel, navController: NavController) {

    var icon = R.drawable.car_icon

    if (orderModel.vehicleType.equals("Car")) {
        icon = R.drawable.car_icon
    } else if (orderModel.vehicleType.equals("Motorcycle")) {
        icon = R.drawable.motorcycle_icon
    } else if (orderModel.vehicleType.equals("Rickshaw")) {
        icon = R.drawable.rickshaw_icon
    } else if (orderModel.vehicleType.equals("Truck")) {
        icon = R.drawable.truck_icon
    } else if (orderModel.vehicleType.equals("Bus")) {
        icon = R.drawable.bus_icon
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(15.dp, 7.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier
                    .padding(15.dp, 0.dp, 7.dp, 0.dp)
                    .clip(RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Spacer(modifier = Modifier.padding(4.dp))
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(MaterialTheme.colorScheme.secondary),
                ) {
                    Icon(
                        painterResource(id = icon),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }

            Column(Modifier.weight(1f)) {

                poppinsBoldText(
                    contentText = orderModel.vehicleOwner,
                    size = 14.sp,
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                )
                poppinsText(
                    contentText = orderModel.vehicleCompany + " " + orderModel.vehicleModel + " | " + orderModel.vehicleFuelType,
                    size = 12.sp,
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                )
                //Spacer(modifier = Modifier.padding(4.dp))
            }
            //Spacer(modifier = Modifier.padding(5.dp))
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(15.dp, 5.dp, 15.dp, 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                Row(
                    modifier = Modifier.border(
                        1.dp, MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(25.dp)
                    )
                ) {
                    Text(
                        text = "Decline",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        modifier = Modifier.padding(15.dp, 5.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(2.dp))
                Row(
                    modifier = Modifier.border(
                        1.dp, MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(25.dp)
                    )
                ) {
                    Text(
                        text = "Accept",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        modifier = Modifier.padding(15.dp, 5.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        navController.navigate("ClientIssueDetailScreen" + "/${orderModel.orderId}" + "/${orderModel.userId}" + "/${orderModel.corporateId}" + "/${orderModel.corporateName}" + "/${orderModel.corporateAddress}" + "/${orderModel.vehicleOwner}" + "/${orderModel.vehicleType}" + "/${orderModel.vehicleCompany}" + "/${orderModel.vehicleModel}" + "/${orderModel.vehicleFuelType}" + "/${orderModel.vehicleLicensePlate}" + "/${orderModel.serviceType}" + "/${orderModel.clientAddress}" + "/${orderModel.clientLatitude}" + "/${orderModel.clientLongitude}" + "/${orderModel.clientAddedText}")
                    }
            ) {
                Text(
                    text = "Details",
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    modifier = Modifier.padding(15.dp, 5.dp)
                )
            }
        }
    }
}
