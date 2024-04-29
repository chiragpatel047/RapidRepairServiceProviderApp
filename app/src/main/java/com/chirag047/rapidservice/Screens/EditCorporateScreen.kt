package com.chirag047.rapidservice.Screens

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.ActionBarWIthBack
import com.chirag047.rapidservice.Common.FullWidthButton
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Common.SnackbarWithoutScaffold
import com.chirag047.rapidservice.Common.customProgressBar
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.R
import com.chirag047.rapidservice.ViewModel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCorporateScreen(navController: NavController, sharedPreferences: SharedPreferences) {
    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState()
        val scope = rememberCoroutineScope()

        val profileViewModel: ProfileViewModel = hiltViewModel()

        val showProgressBar = remember {
            mutableStateOf(false)
        }

        var openMySnackbar = remember { mutableStateOf(false) }
        var snackBarMsg = remember { mutableStateOf("") }


        Column(Modifier.fillMaxWidth()) {
            ActionBarWIthBack(title = "Edit corporate")

            var centerName by remember { mutableStateOf("") }
            var centerAddress by remember { mutableStateOf("") }
            var centerPhoneNo by remember { mutableStateOf("") }
            var centerTime by remember { mutableStateOf("") }

            val centerId = sharedPreferences.getString("corporateId", "")!!


            LaunchedEffect(key1 = Unit) {
                scope.launch(Dispatchers.Main) {
                    profileViewModel.getSingleCenterDetails(centerId).collect {
                        when (it) {
                            is ResponseType.Error -> {

                            }

                            is ResponseType.Loading -> {

                            }

                            is ResponseType.Success -> {
                                centerName = it.data?.centerName!!
                                centerAddress = it.data?.centerAddress!!
                                centerPhoneNo = it.data?.centerPhoneNo!!
                                centerTime = it.data?.centerTime!!
                            }
                        }
                    }
                }
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll)
            ) {


                poppinsBoldText(
                    contentText = "Name of corporate",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 5.dp, 15.dp, 0.dp)
                )

                TextField(
                    value = centerName,
                    singleLine = true,
                    onValueChange = { centerName = it },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "Ex. Sodhi Garage",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )
                    },

                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))

                )


                poppinsBoldText(
                    contentText = "Corporate address",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 15.dp, 15.dp, 0.dp)
                )

                TextField(
                    value = centerAddress,
                    singleLine = true,
                    onValueChange = { centerAddress = it },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "Enter full address here",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )
                    },

                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))

                )


                poppinsBoldText(
                    contentText = "Corporate Phone No.",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 15.dp, 15.dp, 0.dp)
                )

                TextField(
                    value = centerPhoneNo,
                    singleLine = true,
                    onValueChange = { centerPhoneNo = it },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "Enter number here",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )
                    },

                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))
                )



                poppinsBoldText(
                    contentText = "Available timing",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 15.dp, 15.dp, 0.dp)
                )

                TextField(
                    value = centerTime,
                    singleLine = true,
                    onValueChange = { centerTime = it },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "Ex. 8AM - 9PM",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )
                    },

                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))
                )
                Spacer(modifier = Modifier.padding(10.dp))
                FullWidthButton(label = "Submit", color = MaterialTheme.colorScheme.primary) {

                    if (centerName.isEmpty()) {
                        snackBarMsg.value = "Please enter name of corporate"
                        openMySnackbar.value = true
                        return@FullWidthButton
                    }

                    if (centerAddress.isEmpty()) {
                        snackBarMsg.value = "Please enter address of corporate"
                        openMySnackbar.value = true
                        return@FullWidthButton
                    }
                    if (centerPhoneNo.isEmpty()) {
                        snackBarMsg.value = "Please enter phone no of corporate"
                        openMySnackbar.value = true
                        return@FullWidthButton
                    }

                    if (centerTime.isEmpty()) {
                        snackBarMsg.value = "Please enter timing of corporate"
                        openMySnackbar.value = true
                        return@FullWidthButton
                    }


                    scope.launch {
                        profileViewModel.updateCenterDetails(
                            centerId,
                            centerName,
                            centerAddress,
                            centerPhoneNo,
                            centerTime
                        ).collect {
                            when (it) {
                                is ResponseType.Error -> {
                                    showProgressBar.value = false
                                    snackBarMsg.value = it.errorMsg.toString()
                                    openMySnackbar.value = true
                                }

                                is ResponseType.Loading -> {
                                    showProgressBar.value = true
                                }

                                is ResponseType.Success -> {
                                    showProgressBar.value = false
                                    snackBarMsg.value = it.data!!
                                    openMySnackbar.value = true
                                }
                            }
                        }
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