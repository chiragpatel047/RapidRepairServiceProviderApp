package com.chirag047.rapidservice.Screens

import android.content.SharedPreferences
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.GrayFilledSimpleButton
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Common.SingleDoneService
import com.chirag047.rapidservice.Common.SingleMechanic
import com.chirag047.rapidservice.Common.SingleSerivceRequest
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.Common.textWithSeeAllText
import com.chirag047.rapidservice.Model.OrderModel
import com.chirag047.rapidservice.R
import com.chirag047.rapidservice.ViewModel.HomeScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController, sharedPreferences: SharedPreferences) {

    Column(Modifier.fillMaxSize()) {

        val scroll = rememberScrollState()
        val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()

        val pendingOrdersList = remember {
            mutableStateOf(mutableListOf(OrderModel()))
        }

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(scroll)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = "Welcome ,",
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 12.sp
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Icon(
                            painter = painterResource(id = R.drawable.manager),
                            contentDescription = "",
                            modifier = Modifier.size(15.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = sharedPreferences.getString("userName", "dear")!!,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp
                        )
                    }
                }
                GrayFilledSimpleButton(imageIcon = R.drawable.notification) {
                    navController.navigate("NotificationScreen")
                }
            }

            Box(
                Modifier
                    .padding(15.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {

                Column(Modifier.fillMaxWidth()) {
                    poppinsBoldCenterText(
                        contentText = sharedPreferences.getString("corporateName", "")!!,
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
                                    text = sharedPreferences.getString("corporateAddress", "")!!,
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
                                    text = sharedPreferences.getString("corporateTime", "")!!,
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

            textWithSeeAllText(title = "Service Request list") {
                navController.navigate("ServiceRequestListScreen")
            }

            LaunchedEffect(key1 = Unit) {
                scope.launch(Dispatchers.Main) {

                    val corporateId = sharedPreferences.getString("corporateId", "")!!

                    Log.d("corporateId", corporateId)

                    homeScreenViewModel.getPendingOrderRequests(corporateId).collect {
                        when (it) {
                            is ResponseType.Error -> {

                            }

                            is ResponseType.Loading -> {

                            }

                            is ResponseType.Success -> {

                                val list = mutableListOf(OrderModel())
                                list.clear()
                                list.addAll(it.data!!)
                                Log.d("corporateId", (it.data!!.toString()))
                                pendingOrdersList.value = list

                            }
                        }
                    }
                }
            }

            loadPendingRequests(pendingOrdersList.value, navController)

            textWithSeeAllText(title = "Your Mechanic list") {
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

@Composable
fun loadPendingRequests(list: List<OrderModel>, navController: NavController) {

    list.forEach {
        var icon = R.drawable.car_icon

        if (it.vehicleType.equals("Car")) {
            icon = R.drawable.car_icon
        } else if (it.vehicleType.equals("Motorcycle")) {
            icon = R.drawable.motorcycle_icon
        } else if (it.vehicleType.equals("Rickshaw")) {
            icon = R.drawable.rickshaw_icon
        } else if (it.vehicleType.equals("Truck")) {
            icon = R.drawable.truck_icon
        } else if (it.vehicleType.equals("Bus")) {
            icon = R.drawable.bus_icon
        }

        SingleSerivceRequest(
            icon, it.vehicleOwner,
            it.vehicleCompany + " " + it.vehicleModel + " | " + it.vehicleFuelType,
            navController
        )

    }

}