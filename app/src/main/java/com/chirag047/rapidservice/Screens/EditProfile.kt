package com.chirag047.rapidservice.Screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.ActionBarWIthBack
import com.chirag047.rapidservice.Common.FullWidthButton
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(navController: NavController) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth()) {

            ActionBarWIthBack(title = "Edit profile")

            Spacer(modifier = Modifier.padding(20.dp))

            Box(
                Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.profile_image_temp),
                    contentDescription = "",
                    Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .align(Alignment.TopCenter)
                )

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .align(Alignment.BottomEnd)
                        .background(MaterialTheme.colorScheme.primary)
                ) {

                    Icon(
                        painterResource(id = R.drawable.edit_icon),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .align(Alignment.Center)
                    )
                }

            }

            var nameText by remember { mutableStateOf("Jone snow") }
            var emailText by remember { mutableStateOf("jonesnow0806@gmail.com") }
            var numberText by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.padding(20.dp))

            poppinsBoldText(
                contentText = "Full name",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            TextField(
                value = nameText,
                singleLine = true,
                onValueChange = { nameText = it },
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
                        text = "Full name",
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

            Spacer(modifier = Modifier.padding(6.dp))

            poppinsBoldText(
                contentText = "Email",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            TextField(
                value = emailText,
                singleLine = true,
                onValueChange = { emailText = it },
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
                        text = "Email",
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

            Spacer(modifier = Modifier.padding(6.dp))

            poppinsBoldText(
                contentText = "Phone number",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            TextField(
                value = numberText,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { numberText = it },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "Enter your number...",
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

        }


        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .wrapContentSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(0.dp, 15.dp, 0.dp, 40.dp)
            ) {
                FullWidthButton(
                    label = "Update",
                    color = MaterialTheme.colorScheme.primary
                ) {
                    // navController.navigate("SelectVehicleForServiceScreen")
                }
            }
        }

    }
}