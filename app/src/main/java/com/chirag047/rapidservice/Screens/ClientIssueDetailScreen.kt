package com.chirag047.rapidservice.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.ActionBarWIthBack
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Common.SnackbarWithoutScaffold
import com.chirag047.rapidservice.Common.customProgressBar
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.Common.poppinsText
import com.chirag047.rapidservice.R
import com.chirag047.rapidservice.ViewModel.HomeScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ClientIssueDetailScreen(
    navController: NavController,
    orderId: String,
    userId: String,
    corporateId: String,
    corporateName: String,
    corporateAddress: String,
    serviceType: String,
    vehicleOwner: String,
    vehicleType: String,
    vehicleCompany: String,
    vehicleModel: String,
    vehicleFuelType: String,
    vehicleLicensePlate: String,
    clientAddress: String,
    clientLatitude: String,
    clientLongitude: String,
    clientAddedText: String
) {
    Box(Modifier.fillMaxSize()) {

        val scope = rememberCoroutineScope()
        val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()


        val declineOrder = homeScreenViewModel.declineOrder.collectAsState()

        val showProgressBar = remember {
            mutableStateOf(false)
        }

        var openMySnackbar = remember { mutableStateOf(false) }
        var snackBarMsg = remember { mutableStateOf("") }


        val scroll = rememberScrollState()
        Column(Modifier.fillMaxWidth()) {
            ActionBarWIthBack(title = "Client Issue details")
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
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
                            detailContent(
                                vehicleOwner,
                                navController,
                                clientLatitude,
                                clientLongitude
                            )
                            detailContent(
                                vehicleType,
                                navController,
                                clientLatitude,
                                clientLongitude
                            )
                            detailContent(
                                vehicleCompany,
                                navController,
                                clientLatitude,
                                clientLongitude
                            )
                            detailContent(
                                vehicleModel,
                                navController,
                                clientLatitude,
                                clientLongitude
                            )
                            detailContent(
                                vehicleFuelType,
                                navController,
                                clientLatitude,
                                clientLongitude
                            )
                            detailContent(
                                vehicleLicensePlate,
                                navController,
                                clientLatitude,
                                clientLongitude
                            )
                        }
                    }
                }

                poppinsBoldText(
                    contentText = "Client Service Request",
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
                            detailTitle(title = "Client Issue type")
                            detailTitle(title = "Client Location")
                            detailTitle(title = "Client Address")
                        }
                        Column() {
                            colanText()
                            colanText()
                            colanText()
                        }
                        Column {
                            detailContent(
                                serviceType,
                                navController,
                                clientLatitude,
                                clientLongitude
                            )
                            detailContent(
                                "Locate Client",
                                navController,
                                clientLatitude,
                                clientLongitude
                            )
                            detailContent(
                                clientAddress,
                                navController,
                                clientLatitude,
                                clientLongitude
                            )
                        }
                    }
                }

                poppinsBoldText(
                    contentText = "Client added Text",
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
                        detailContent(
                            clientAddedText,
                            navController,
                            clientLatitude,
                            clientLongitude
                        )
                    }
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .shadow(10.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 25.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .border(
                                1.dp, MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(25.dp)
                            )
                            .weight(1f)
                            .clickable {
                                scope.launch(Dispatchers.IO) {
                                    homeScreenViewModel.declineOrder(orderId, userId, corporateName)

                                    when (declineOrder.value) {
                                        is ResponseType.Error -> {
                                            showProgressBar.value = false
                                            snackBarMsg.value =
                                                declineOrder.value.errorMsg.toString()
                                            openMySnackbar.value = true
                                        }

                                        is ResponseType.Loading -> {
                                            showProgressBar.value = true
                                        }

                                        is ResponseType.Success -> {
                                            showProgressBar.value = false
                                            snackBarMsg.value = declineOrder.value.data!!
                                            openMySnackbar.value = true
                                        }
                                    }
                                }
                            }
                    ) {
                        Text(
                            text = "Decline",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            modifier = Modifier
                                .padding(15.dp, 10.dp)
                                .weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(25.dp))
                            .background(MaterialTheme.colorScheme.primary)
                            .weight(1f)
                            .clickable {
                                navController.navigate("SelectMechanicForService" + "/$orderId" + "/$userId"+ "/$corporateName" )
                            }
                    ) {
                        Text(
                            text = "Accept",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            modifier = Modifier
                                .padding(15.dp, 10.dp)
                                .weight(1f)
                        )
                    }
                }
            }
        }
        customProgressBar(show = showProgressBar.value, title = "Wait a moment...")

        SnackbarWithoutScaffold(
            snackBarMsg.value, openMySnackbar.value, { openMySnackbar.value = it }, Modifier.align(
                Alignment.BottomCenter
            )
        )

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
fun detailContent(
    content: String,
    navController: NavController,
    clientLatitude: String,
    clientLongitude: String
) {
    Text(
        text = content,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 14.sp,
        textDecoration = if (content.equals("Locate Client")) TextDecoration.Underline else TextDecoration.None,
        color = if (content.equals("Locate Client")) MaterialTheme.colorScheme.primary else Color.Unspecified,
        modifier = Modifier
            .padding(15.dp, 5.dp, 15.dp, 0.dp)
            .clickable {
                if (content.equals("Locate Client")) {
                    navController.navigate("ClientLocationScreen" + "/$clientLatitude" + "/$clientLongitude")
                }
            }
    )
}

