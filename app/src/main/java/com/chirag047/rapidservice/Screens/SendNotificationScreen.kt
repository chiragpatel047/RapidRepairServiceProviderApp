package com.chirag047.rapidservice.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
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
import com.chirag047.rapidservice.ViewModel.SendNotificationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendNotificationScreen(navController: NavController, mechanicId: String) {
    Box(modifier = Modifier.fillMaxSize()) {

        val sendNotificationViewModel: SendNotificationViewModel = hiltViewModel()

        val scope = rememberCoroutineScope()

        val showProgressBar = remember {
            mutableStateOf(false)
        }

        var openMySnackbar = remember { mutableStateOf(false) }
        var snackBarMsg = remember { mutableStateOf("") }

        var title by remember { mutableStateOf("") }
        var desc by remember { mutableStateOf("") }

        Column(Modifier.fillMaxWidth()) {

            ActionBarWIthBack(title = "Send Notification")

            Spacer(modifier = Modifier.padding(20.dp))

            poppinsBoldText(
                contentText = "Title",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            TextField(
                value = title,
                singleLine = true,
                onValueChange = { title = it },
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
                        text = "Enter notification title here...",
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
                contentText = "Description",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 0.dp)
            )

            TextField(
                value = desc,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                ),
                onValueChange = { desc = it },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "Enter notification description here...",
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
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(0.dp, 0.dp, 0.dp, 30.dp)
        ) {
            FullWidthButton(label = "Send", color = MaterialTheme.colorScheme.primary) {
                if (title.isEmpty()) {
                    snackBarMsg.value = "Title can't be empty"
                    openMySnackbar.value = true
                    return@FullWidthButton
                }

                if (desc.isEmpty()) {
                    snackBarMsg.value = "Description can't be empty"
                    openMySnackbar.value = true
                    return@FullWidthButton
                }

                scope.launch(Dispatchers.IO) {
                    sendNotificationViewModel.sendCustomNotificationToMechanic(
                        mechanicId,
                        title,
                        desc
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
                                snackBarMsg.value = it.data.toString()
                                openMySnackbar.value = true

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