package com.chirag047.rapidservice.Screens

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.FullWidthButton
import com.chirag047.rapidservice.Common.GrayFilledSimpleButton
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Common.SelectMechanicFromList
import com.chirag047.rapidservice.Common.SingleMechanic
import com.chirag047.rapidservice.Common.SnackbarWithoutScaffold
import com.chirag047.rapidservice.Common.customProgressBar
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.Model.MechanicModel
import com.chirag047.rapidservice.Model.OrderModel
import com.chirag047.rapidservice.R
import com.chirag047.rapidservice.ViewModel.AddMechanicViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewMechanicScreen(navController: NavController, sharedPreferences: SharedPreferences) {

    val addMechanicViewModel: AddMechanicViewModel = hiltViewModel()
    val scroll = rememberScrollState()
    val scope = rememberCoroutineScope()

    val showProgressBar = remember {
        mutableStateOf(false)
    }

    val mechanicList = remember {
        mutableListOf<MechanicModel>()
    }
    var selectedIndex = remember { mutableStateOf(-1) }

    var openMySnackbar = remember { mutableStateOf(false) }
    var snackBarMsg = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(Modifier.fillMaxWidth()) {
            Column(Modifier.fillMaxWidth()) {
                poppinsBoldCenterText(
                    contentText = "Add new mechanic",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()

            ) {
                var mechanicIdText by remember { mutableStateOf("") }

                poppinsBoldText(
                    contentText = "Fetch Mechanic",
                    size = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 15.dp, 15.dp, 0.dp)
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = mechanicIdText,
                        singleLine = true,
                        onValueChange = { mechanicIdText = it },
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
                                text = "Enter mechanic id here...",
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium))
                            )
                        },

                        modifier = Modifier
                            .background(Color.Transparent)
                            .weight(1f)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    GrayFilledSimpleButton(imageIcon = R.drawable.search) {

                        if (mechanicIdText.isEmpty()) {
                            snackBarMsg.value = "Please enter mechanic id"
                            openMySnackbar.value = true
                            return@GrayFilledSimpleButton
                        }
                        scope.launch(Dispatchers.Main) {
                            addMechanicViewModel.searchMechanic(mechanicIdText).collect {
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
                                        mechanicList.clear()
                                        mechanicList.addAll(it.data!!)
                                    }
                                }
                            }
                        }
                    }
                }

                selectedIndex.value = SelectMechanicFromList(mechanicList)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(0.dp, 0.dp, 0.dp, 30.dp)
        ) {
            FullWidthButton(label = "Add", MaterialTheme.colorScheme.primary) {
                if (selectedIndex.value.equals(-1)) {
                    snackBarMsg.value = "Please select mechanic"
                    openMySnackbar.value = true
                    return@FullWidthButton
                }

                val mechanicUid = mechanicList.get(selectedIndex.value).uid
                val centerId = sharedPreferences.getString("corporateId", "")
                val centerName = sharedPreferences.getString("corporateName", "")

                Log.d(
                    "updatingMMData",
                    mechanicUid.toString() + centerId.toString() + centerName.toString()
                )

                scope.launch(Dispatchers.Main) {
                    addMechanicViewModel.addNewMechanic(mechanicUid, centerId!!, centerName!!)
                        .collect {
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


