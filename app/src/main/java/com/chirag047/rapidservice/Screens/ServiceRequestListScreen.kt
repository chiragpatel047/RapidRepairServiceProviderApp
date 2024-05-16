package com.chirag047.rapidservice.Screens

import android.content.SharedPreferences
import android.util.Log
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
import com.chirag047.rapidservice.Common.SingleSerivceRequest
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Model.MechanicModel
import com.chirag047.rapidservice.Model.OrderModel
import com.chirag047.rapidservice.R
import com.chirag047.rapidservice.ViewModel.HomeScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ServiceRequestListScreen(navController: NavController, sharedPreferences: SharedPreferences) {

    val scroll = rememberScrollState()

    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()

    val scope = rememberCoroutineScope()

    val pendingOrdersList = remember {
        mutableListOf<OrderModel>()
    }

    val result = homeScreenViewModel.pendingList.collectAsState()

    val pendingStatus = remember {
        mutableStateOf("Loading...")
    }

    Column(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth()) {
            poppinsBoldCenterText(
                contentText = "Service Request list",
                size = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(scroll)
        ) {

            LaunchedEffect(key1 = Unit) {
                scope.launch(Dispatchers.IO) {
                    val corporateId = sharedPreferences.getString("corporateId", "")!!
                    homeScreenViewModel.getPendingOrderRequests(corporateId)
                }
            }

            when (result.value) {
                is ResponseType.Error -> {

                }

                is ResponseType.Loading -> {

                }

                is ResponseType.Success -> {

                    pendingOrdersList.clear()
                    pendingOrdersList.addAll(result.value.data!!)
                    pendingStatus.value = "No pending requests"
                }
            }

            loadPendingRequests(pendingOrdersList.reversed(),navController,homeScreenViewModel)
            NoDataText(
                text = pendingStatus.value,
                isVisible = pendingOrdersList.size.equals(0)
            )
        }
    }
}