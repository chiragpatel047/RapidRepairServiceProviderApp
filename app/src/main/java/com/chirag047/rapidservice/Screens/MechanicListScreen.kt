package com.chirag047.rapidservice.Screens

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.NoDataText
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Common.SingleMechanic
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Model.MechanicModel
import com.chirag047.rapidservice.Model.OrderModel
import com.chirag047.rapidservice.ViewModel.HomeScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MechanicListScreen(navController: NavController, sharedPreferences: SharedPreferences) {

    val scroll = rememberScrollState()

    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    val mechanicList = remember {
        mutableListOf<MechanicModel>()
    }

    val mechanicStatus = remember {
        mutableStateOf("Loading...")
    }

    val mechanicListState = homeScreenViewModel.mechanicList.collectAsState()


    Column(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth()) {
            poppinsBoldCenterText(
                contentText = "Your Mechanic list",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(scroll)) {
            LaunchedEffect(key1 = Unit) {
                scope.launch(Dispatchers.IO) {
                    val corporateId = sharedPreferences.getString("corporateId", "")!!
                    homeScreenViewModel.getMyAllMechanics(corporateId)
                }
            }

            when (mechanicListState.value) {
                is ResponseType.Error -> {

                }

                is ResponseType.Loading -> {

                }

                is ResponseType.Success -> {
                    mechanicList.clear()
                    mechanicList.addAll(mechanicListState.value.data!!)
                    mechanicStatus.value = "You haven't added any mechanic"
                }
            }

            loadMechanicList(mechanicList, navController)

            NoDataText(
                text = mechanicStatus.value,
                isVisible = mechanicList.size.equals(0)
            )
        }
    }
}