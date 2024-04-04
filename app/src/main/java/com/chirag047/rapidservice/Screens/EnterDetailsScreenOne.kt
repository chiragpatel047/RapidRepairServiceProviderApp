package com.chirag047.rapidservice.Screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.FullWidthButton
import com.chirag047.rapidservice.Common.SnackbarWithoutScaffold
import com.chirag047.rapidservice.Common.customProgressBar
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.Common.poppinsText
import com.chirag047.rapidservice.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterDetailsScreenOne(navController: NavController) {

    val showProgressBar = remember {
        mutableStateOf(false)
    }

    var openMySnackbar = remember { mutableStateOf(false) }
    var snackBarMsg = remember { mutableStateOf("") }

    Box(Modifier.fillMaxSize()) {

        val scroll = rememberScrollState()
        Column(Modifier.fillMaxWidth()) {

            Column(Modifier.fillMaxWidth()) {
                poppinsBoldCenterText(
                    contentText = "Please Fill Details",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll)
            ) {

                var centerName by remember { mutableStateOf("") }

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

                var centerAddress by remember { mutableStateOf("") }

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
                    contentText = "Choose city",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 15.dp, 15.dp, 0.dp)
                )

                val citiesInIndia = listOf(
                    "Ahmedabad",
                    "Bangalore",
                    "Bhopal",
                    "Chennai",
                    "Delhi",
                    "Ghaziabad",
                    "Hyderabad",
                    "Indore",
                    "Jaipur",
                    "Kanpur",
                    "Kolkata",
                    "Lucknow",
                    "Mumbai",
                    "Nagpur",
                    "Patna",
                    "Pune",
                    "Surat",
                    "Thane",
                    "Vadodara",
                    "Visakhapatnam"
                )

                var selectedCity = remember {
                    mutableStateOf("Select city")
                }

                val showDialog = remember { mutableStateOf(false) }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))
                    .clickable {
                        showDialog.value = !showDialog.value
                    }) {
                    Text(
                        text = selectedCity.value,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        modifier = Modifier.padding(15.dp)
                    )
                }

                if (showDialog.value)
                    CustomDialog(list = citiesInIndia, setShowDialog = { isShow, selected ->
                        showDialog.value = isShow
                        selectedCity.value = selected
                        Log.d("CusomeDialogCustomeValue", selected)
                    })

                var centerPhoneNo by remember { mutableStateOf("") }

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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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


                var centerTime by remember { mutableStateOf("") }

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
                FullWidthButton(label = "Next", color = MaterialTheme.colorScheme.primary) {

                    if (centerName.isEmpty() || centerAddress.isEmpty() || centerPhoneNo.isEmpty() || centerTime.isEmpty()) {
                        snackBarMsg.value = "Please fill all details"
                        openMySnackbar.value = true
                        return@FullWidthButton
                    }

                    if (selectedCity.value.equals("Select city")) {
                        snackBarMsg.value = "Please select city"
                        openMySnackbar.value = true
                        return@FullWidthButton
                    }
                    navController.navigate("ChooseLocationOnMapScreen" + "/$centerName" + "/$centerAddress" + "/${selectedCity.value}" + "/$centerPhoneNo" + "/$centerTime")
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
fun CustomDialog(
    list: List<String>,
    setShowDialog: (Boolean, String) -> Unit
) {

    var selected = "Select city"

    Dialog(onDismissRequest = {
        setShowDialog(false, selected)
    }) {
        Surface {
            val scroll = rememberScrollState()
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(scroll)
            ) {
                list.forEach {
                    poppinsText(
                        contentText = it,
                        size = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .clickable {
                                selected = it
                                setShowDialog(false, selected)
                            }
                    )
                }
            }
        }
    }
}