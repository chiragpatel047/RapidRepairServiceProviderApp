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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.SingleDoneService
import com.chirag047.rapidservice.Common.SingleMechanic
import com.chirag047.rapidservice.Common.SingleSerivceRequest
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.Common.textWithSeeAllText
import com.chirag047.rapidservice.R

@Composable
fun HomeScreen(navController: NavController) {

    Column(Modifier.fillMaxSize()) {

        val scroll = rememberScrollState()

        Column(Modifier.fillMaxWidth()) {
            poppinsBoldCenterText(
                contentText = "Home",
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

            Box(
                Modifier
                    .padding(15.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {

                Column(Modifier.fillMaxWidth()) {
                    poppinsBoldCenterText(
                        contentText = "Sodhi Garage Limited",
                        size = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 15.dp, 15.dp, 5.dp)
                    )

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 15.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.background),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Column(
                            Modifier
                                .weight(1f)
                                .padding(10.dp, 15.dp, 15.dp, 0.dp)
                        ) {
                            Row() {
                                Icon(
                                    painter = painterResource(id = R.drawable.location_pin_icon),
                                    contentDescription = "",
                                    Modifier
                                        .size(30.dp)
                                        .padding(0.dp, 8.dp, 0.dp, 8.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "4063, Waterview Lane, New Maxico",
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .padding(0.dp, 6.dp, 0.dp, 5.dp),
                                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.clock),
                                    contentDescription = "",
                                    Modifier
                                        .size(30.dp)
                                        .padding(0.dp, 8.dp, 0.dp, 8.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "8AM - 9PM",
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .padding(0.dp, 6.dp, 0.dp, 5.dp),
                                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                                )
                            }

                            Spacer(modifier = Modifier.padding(8.dp))

                        }

                        Button(
                            onClick = {

                            },
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        ) {
                            Text(
                                text = "Available",
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            )
                        }
                    }
                }
            }

            textWithSeeAllText(title = "Service Request list"){
                navController.navigate("ServiceRequestListScreen")
            }

            //SingleSerivceRequest(R.drawable.car_icon,"Jone snow","TATA Aviniya | Battery")
            SingleSerivceRequest(R.drawable.motorcycle_icon, "Mukesh patel", "Yamaha R15 | Petrol")
            SingleSerivceRequest(R.drawable.car_icon, "Tushar gohil", "Maruti Swift | Diesel")

            textWithSeeAllText(title = "Your Mechanic list"){
                navController.navigate("MechanicListScreen")
            }

            SingleMechanic("Apurva Gandhi", "Available")
            SingleMechanic("Chintan Gajjar", "Currently on service")
            SingleMechanic("Papesh Padhare", "Not available")

            //SingleDoneService(R.drawable.car_icon,"Ashish Kharawar","Mahindra Thar | Diesel")
            //SingleDoneService(R.drawable.motorcycle_icon,"Ankit Kharawar","Hero Splender | Petrol")
            //SingleDoneService(R.drawable.rickshaw_icon,"Mukesh patel","Auto Rickshaw | CNG")

        }

    }
}