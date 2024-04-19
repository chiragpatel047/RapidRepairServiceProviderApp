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
import androidx.compose.runtime.collectAsState
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
import com.chirag047.rapidservice.Common.NoDataText
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Common.SingleDoneService
import com.chirag047.rapidservice.Common.SingleMechanic
import com.chirag047.rapidservice.Common.SingleSerivceRequest
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.Common.textWithSeeAllText
import com.chirag047.rapidservice.Model.MechanicModel
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
            mutableListOf<OrderModel>()
        }
        val mechanicList = remember {
            mutableListOf<MechanicModel>()
        }

        val pendingStatus = remember {
            mutableStateOf("Loading...")
        }
        val mechanicStatus = remember {
            mutableStateOf("Loading...")
        }

        val centerName = remember {
            mutableStateOf("...")
        }
        val centerAddress = remember {
            mutableStateOf("...")
        }

        val centerTime = remember {
            mutableStateOf("...")
        }

        val centerStatus = remember {
            mutableStateOf("...")
        }

        val pendingListState = homeScreenViewModel.pendingList.collectAsState()
        val mechanicListState = homeScreenViewModel.mechanicList.collectAsState()
        val centerDetails = homeScreenViewModel.centerDetails.collectAsState()

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
                    .background(if (centerStatus.value.equals("Available")) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer)
            ) {

                LaunchedEffect(key1 = Unit) {
                    scope.launch(Dispatchers.IO) {
                        val corporateId = sharedPreferences.getString("corporateId", "")!!
                        homeScreenViewModel.getSingleCenterDetails(corporateId)
                    }
                }

                when (centerDetails.value) {
                    is ResponseType.Error -> {

                    }

                    is ResponseType.Loading -> {

                    }

                    is ResponseType.Success -> {
                        centerName.value = centerDetails.value.data!!.centerName!!
                        centerAddress.value = centerDetails.value.data!!.centerAddress!!
                        centerTime.value = centerDetails.value.data!!.centerTime!!
                        centerStatus.value = centerDetails.value.data!!.centerStatus!!
                    }
                }

                Column(Modifier.fillMaxWidth()) {
                    poppinsBoldCenterText(
                        contentText = centerName.value,
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
                                    tint = if (centerStatus.value.equals("Available")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                                )

                                Text(
                                    text = centerAddress.value,
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
                                    tint = if (centerStatus.value.equals("Available")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                                )

                                Text(
                                    text = centerTime.value,
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
                                val corporateId =
                                    sharedPreferences.getString("corporateId", "")!!
                                if (centerStatus.value.equals("Available")) {

                                    scope.launch(Dispatchers.IO) {

                                        homeScreenViewModel.updateCenterStatus(
                                            corporateId,
                                            "Closed"
                                        )
                                    }
                                } else {
                                    scope.launch(Dispatchers.IO) {

                                        homeScreenViewModel.updateCenterStatus(
                                            corporateId,
                                            "Available"
                                        )
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(if (centerStatus.value.equals("Available")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error),
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        ) {
                            Text(
                                text = centerStatus.value,
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
                scope.launch(Dispatchers.IO) {
                    val corporateId = sharedPreferences.getString("corporateId", "")!!
                    homeScreenViewModel.getPendingOrderRequests(corporateId)
                }
            }

            when (pendingListState.value) {
                is ResponseType.Error -> {

                }

                is ResponseType.Loading -> {

                }

                is ResponseType.Success -> {

                    pendingOrdersList.clear()
                    pendingOrdersList.addAll(pendingListState.value.data!!)
                    pendingStatus.value = "No pending requests"
                }
            }

            loadPendingRequests(pendingOrdersList.take(3), navController)
            NoDataText(
                text = pendingStatus.value,
                isVisible = pendingOrdersList.size.equals(0)
            )

            textWithSeeAllText(title = "Your Mechanic list") {
                navController.navigate("MechanicListScreen")
            }

            LaunchedEffect(key1 = Unit) {
                scope.launch(Dispatchers.IO) {
                    val corporateId = sharedPreferences.getString("corporateId", "")!!
                    homeScreenViewModel.getMyAllMechanics(corporateId)
                }
            }

            when (mechanicListState.value) {
                is ResponseType.Error -> {

                }

                is ResponseType.Loading -> {

                }

                is ResponseType.Success -> {
                    mechanicList.clear()
                    mechanicList.addAll(mechanicListState.value.data!!)
                    mechanicStatus.value = "You haven't added any mechanic"
                }
            }

            loadMechanicList(mechanicList.take(3), navController)

            NoDataText(
                text = mechanicStatus.value,
                isVisible = mechanicList.size.equals(0)
            )
        }
    }
}

@Composable
fun loadPendingRequests(list: List<OrderModel>, navController: NavController) {

    list.forEach {
        SingleSerivceRequest(
            it,
            navController
        )

    }

}

@Composable
fun loadMechanicList(list: List<MechanicModel>, navController: NavController) {

    list.forEach {

        SingleMechanic(it, navController)

    }

}