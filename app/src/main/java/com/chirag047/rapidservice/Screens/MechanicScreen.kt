package com.chirag047.rapidservice.Screens

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.ManageSingleMechanic
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Model.MechanicModel
import com.chirag047.rapidservice.R
import com.chirag047.rapidservice.ViewModel.MechanicScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MechanicScreen(navController: NavController, sharedPreferences: SharedPreferences) {

    val scope = rememberCoroutineScope()
    val mechanicScreenViewModel: MechanicScreenViewModel = hiltViewModel()

    val mechanicList = remember {
        mutableListOf<MechanicModel>()
    }

    val centerId = sharedPreferences.getString("corporateId", "")

    LaunchedEffect(key1 = Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            mechanicScreenViewModel.getMyAllMechanics(centerId!!)
        }
    }

    val state = mechanicScreenViewModel.mechanicData.collectAsState()


    Box(Modifier.fillMaxSize()) {

        when (state.value) {
            is ResponseType.Error -> {

            }

            is ResponseType.Loading -> {

            }

            is ResponseType.Success -> {
                mechanicList.clear()
                mechanicList.addAll(state.value.data!!)

                Column(Modifier.fillMaxWidth()) {
                    Column(Modifier.fillMaxWidth()) {
                        poppinsBoldCenterText(
                            contentText = "Mechanics",
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
                        loadMechanics(mechanicList, mechanicScreenViewModel)
                    }
                }

            }
        }

        ExtendedFloatingActionButton(
            text = {
                Text(
                    text = "New Mechanic",
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            },
            onClick = {
                navController.navigate("AddNewMechanicScreen")
            },
            icon = { Icon(Icons.Filled.Add, "") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp, 20.dp)
        )

    }
}

@Composable
fun loadMechanics(list: List<MechanicModel>, mechanicScreenViewModel: MechanicScreenViewModel) {
    val scope = rememberCoroutineScope()

    list.forEach {
        ManageSingleMechanic(it.userName, it.mechanicStatus) {
            scope.launch(Dispatchers.Main) {
                mechanicScreenViewModel.deleteMechanic(it.uid).collect {
                    when (it) {
                        is ResponseType.Error -> {

                        }

                        is ResponseType.Loading -> {

                        }

                        is ResponseType.Success -> {

                        }
                    }
                }
            }
        }
    }
}