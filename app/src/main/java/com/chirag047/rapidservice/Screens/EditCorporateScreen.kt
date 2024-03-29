package com.chirag047.rapidservice.Screens

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.ActionBarWIthBack
import com.chirag047.rapidservice.Common.FullWidthButton
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCorporateScreen(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState()

        Column(Modifier.fillMaxWidth()) {
            ActionBarWIthBack(title = "Edit corporate")

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

                var centerLatLong by remember { mutableStateOf("Choose location on map") }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = centerLatLong,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        modifier = Modifier
                            .weight(1f)
                            .padding(20.dp, 20.dp)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.target),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(5.dp)
                    )
                    Spacer(modifier = Modifier.padding(4.dp))

                }


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
                FullWidthButton(label = "Submit", color = MaterialTheme.colorScheme.primary) {

                }
            }

        }
    }
}