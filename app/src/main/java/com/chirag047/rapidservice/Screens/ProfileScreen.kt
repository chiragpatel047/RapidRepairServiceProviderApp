package com.chirag047.rapidservice.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.Common.poppinsText
import com.chirag047.rapidservice.R

@Composable
fun ProfileScreen(navController: NavController) {
    Box(Modifier.fillMaxSize()) {

        Column(Modifier.fillMaxWidth()) {
            poppinsBoldCenterText(
                contentText = "Profile",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            val scroll = rememberScrollState()
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(scroll)
            ) {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 15.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_image_temp),
                        contentDescription = "",
                        Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(50.dp))
                    )

                    Spacer(modifier = Modifier.padding(4.dp))
                    
                    Column() {
                        Text(
                            text = "Jon snow",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 0.dp)
                        )
                        Text(
                            text = "jonesnow0806@gmail.com",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 0.dp)
                        )
                    }

                }

                Spacer(modifier = Modifier.padding(10.dp))

                poppinsBoldText(
                    contentText = "Account Overview",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )

                singleSetting(
                    icon = R.drawable.profile_filled_icon,
                    title = "My Profile",
                    desc = "Edit your personal information"
                ) {
                    navController.navigate("EditProfile")
                }

                singleSetting(
                    icon = R.drawable.service_center,
                    title = "My corporate",
                    desc = "Edit your corporate information"
                ) {
                    navController.navigate("EditProfile")
                }

                singleSetting(
                    icon = R.drawable.notification,
                    title = "Notifications",
                    desc = "Updates and much more"
                ) {
                    navController.navigate("NotificationScreen")
                }

                singleSetting(
                    icon = R.drawable.edit_icon,
                    title = "Password",
                    desc = "Change your password"
                ) {
                    navController.navigate("ChangePasswordScreen")
                }

                singleSetting(
                    icon = R.drawable.termsconditions_icon,
                    title = "Terms & Conditions",
                    desc = "Read our terms and conditions"
                ) {

                }

                singleSetting(
                    icon = R.drawable.logout,
                    title = "Logout",
                    desc = ""
                ) {

                }
            }
        }

    }
}

@Composable
fun singleSetting(icon: Int, title: String, desc: String, onclick: () -> Unit) {
    Row(
        Modifier
            .padding(15.dp, 7.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable {
                onclick.invoke()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .padding(15.dp, 0.dp, 7.dp, 0.dp)
                .clip(RoundedCornerShape(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Spacer(modifier = Modifier.padding(10.dp))
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
            Spacer(modifier = Modifier.padding(10.dp))
        }

        Column(Modifier.weight(1f)) {

            poppinsBoldText(
                contentText = title,
                size = 14.sp,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
            )

            if (!title.equals("Logout")) {
                poppinsText(
                    contentText = desc,
                    size = 12.sp,
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                )
            }

        }

        if (!title.equals("Logout")) {
            Icon(
                painterResource(id = R.drawable.down_arrow_icon),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .size(30.dp)
                    .padding(8.dp)
                    .rotate(270f)
            )
        }

        Spacer(modifier = Modifier.padding(5.dp))

    }
}