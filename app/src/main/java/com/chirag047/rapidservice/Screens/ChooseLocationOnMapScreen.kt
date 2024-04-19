package com.chirag047.rapidservice.Screens

import android.content.SharedPreferences
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.chirag047.rapidservice.Common.ActionBarWIthBack
import com.chirag047.rapidservice.Common.FullWidthButton
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Common.SnackbarWithoutScaffold
import com.chirag047.rapidservice.Common.customProgressBar
import com.chirag047.rapidservice.Model.CenterModel
import com.chirag047.rapidservice.R
import com.chirag047.rapidservice.ViewModel.ChooseLocationOnMapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun ChooseLocationOnMapScreen(
    navController: NavController,
    corporateName: String,
    corporateAddress: String,
    corporateCity: String,
    corporatePhoneNo: String,
    corporateTime: String,
    sharedPreferences: SharedPreferences
) {

    val loadMap = remember {
        mutableStateOf(false)
    }

    val latitude = remember {
        mutableStateOf("")
    }
    val longitude = remember {
        mutableStateOf("")
    }

    val chooseLocationOnMapViewModel: ChooseLocationOnMapViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        delay(500)
        loadMap.value = true
    }

    Box(Modifier.fillMaxSize()) {
        val showProgressBar = remember {
            mutableStateOf(false)
        }
        val currentCoords = remember {
            mutableStateOf("Click on map where your corporate is locate")
        }

        var openMySnackbar = remember { mutableStateOf(false) }
        var snackBarMsg = remember { mutableStateOf("") }

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                ActionBarWIthBack(title = "Choose Location")
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.Black)
            ) {
                screen(load = loadMap.value, onClick = { lat, long ->
                    latitude.value = lat
                    longitude.value = long
                    currentCoords.value = "Latitude : " + lat + "\nLongitude : " + long
                })
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                    .shadow(10.dp, RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                    .background(MaterialTheme.colorScheme.background)
            ) {

                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(80.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(MaterialTheme.colorScheme.outline)
                ) {

                }

                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 0.dp)
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.pin_icon),
                        contentDescription = "",
                        modifier = Modifier.size(15.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = currentCoords.value,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(10.dp)
                    )

                }

                Spacer(modifier = Modifier.padding(5.dp))

                Box(
                    Modifier
                        .wrapContentSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Column(
                        Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(0.dp, 15.dp, 0.dp, 40.dp)
                    ) {
                        FullWidthButton(
                            label = "Confirm location",
                            color = MaterialTheme.colorScheme.primary
                        ) {

                            if (currentCoords.value.equals("Click on map where your corporate is locate")) {
                                snackBarMsg.value = "Please choose location"
                                openMySnackbar.value = true
                                return@FullWidthButton
                            }

                            val centerModel = CenterModel(
                                System.currentTimeMillis().toString(),
                                "Available",
                                corporateName,
                                corporateAddress,
                                corporateTime,
                                corporatePhoneNo,
                                "0.0",
                                corporateCity,
                                latitude.value,
                                longitude.value
                            )

                            scope.launch(Dispatchers.Main) {
                                chooseLocationOnMapViewModel.createNewCenter(centerModel).collect {
                                    when (it) {
                                        is ResponseType.Success -> {

                                            showProgressBar.value = false

                                            sharedPreferences.edit()
                                                .putString("corporateName", corporateName).apply()
                                            sharedPreferences.edit()
                                                .putString("corporateAddress", corporateAddress)
                                                .apply()
                                            sharedPreferences.edit()
                                                .putString("corporateTime", corporateTime).apply()
                                            sharedPreferences.edit()
                                                .putString("corporateId", centerModel.centerId)
                                                .apply()

                                            navController.popBackStack()
                                            navController.popBackStack()
                                            navController.popBackStack()
                                            navController.popBackStack()
                                            navController.popBackStack()

                                            navController.navigate("MainScreen")


                                        }

                                        is ResponseType.Loading -> {
                                            showProgressBar.value = true
                                        }

                                        is ResponseType.Error -> {
                                            showProgressBar.value = false
                                            snackBarMsg.value = it.errorMsg.toString()
                                            openMySnackbar.value = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        customProgressBar(show = showProgressBar.value, title = "Wait a moment...")

        SnackbarWithoutScaffold(
            snackBarMsg.value,
            openMySnackbar.value,
            { openMySnackbar.value = it },
            Modifier.align(
                Alignment.BottomCenter
            )
        )
    }
}

@Composable
fun screen(load: Boolean, onClick: (lat: String, long: String) -> Unit) {
    AnimatedVisibility(visible = load) {
        val markerState =
            remember { mutableStateOf(MarkerState(position = LatLng(0.0, 0.0))) }

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(24.6, 85.7), 3f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = { clickCoordinates ->
                // Extract lat and lng from clickCoordinates
                val latitude = clickCoordinates.latitude
                val longitude = clickCoordinates.longitude
                markerState.value = MarkerState(position = clickCoordinates)
                onClick.invoke(latitude.toString(), longitude.toString())
            }
        ) {
            if (!markerState.value.position.latitude.equals(0.0)) {
                Marker(state = markerState.value)
            }
        }
    }
}