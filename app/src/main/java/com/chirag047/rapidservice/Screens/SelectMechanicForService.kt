package com.chirag047.rapidservice.Screens

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.FullWidthButton
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Common.SelectMechanicFromList
import com.chirag047.rapidservice.Common.SnackbarWithoutScaffold
import com.chirag047.rapidservice.Common.customProgressBar
import com.chirag047.rapidservice.Common.poppinsBoldCenterText
import com.chirag047.rapidservice.Common.poppinsBoldText
import com.chirag047.rapidservice.Common.poppinsText
import com.chirag047.rapidservice.Model.MechanicModel
import com.chirag047.rapidservice.R
import com.chirag047.rapidservice.ViewModel.MechanicScreenViewModel
import com.chirag047.rapidservice.ViewModel.SelectMechanicForServiceViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SelectMechanicForService(
    navController: NavController, sharedPreferences: SharedPreferences, orderId: String
) {

    val scope = rememberCoroutineScope()
    val selectMechanicScreenViewModel: SelectMechanicForServiceViewModel = hiltViewModel()

    val mechanicList = remember {
        mutableListOf<MechanicModel>()
    }

    val showProgressBar = remember {
        mutableStateOf(false)
    }

    var selectedIndex = remember { mutableStateOf(-1) }

    var openMySnackbar = remember { mutableStateOf(false) }
    var snackBarMsg = remember { mutableStateOf("") }

    val centerId = sharedPreferences.getString("corporateId", "")

    LaunchedEffect(key1 = Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            selectMechanicScreenViewModel.getMyAllMechanics(centerId!!)
        }
    }

    val state = selectMechanicScreenViewModel.mechanicData.collectAsState()


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
                            contentText = "Select Mechanic",
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
                        selectedIndex.value = SelectMechanicForServiceFromList(mechanicList)
                    }
                }

            }
        }


        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
        ) {

            Box(
                Modifier
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
                        label = "Submit to mechanic",
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        if (selectedIndex.value.equals(-1)) {
                            snackBarMsg.value = "Please select mechanic"
                            openMySnackbar.value = true
                            return@FullWidthButton
                        }

                        val selectedMechanic = mechanicList.get(selectedIndex.value)

                        scope.launch(Dispatchers.Main) {
                            selectMechanicScreenViewModel.submitOrderToMechanic(
                                orderId,
                                selectedMechanic.mechanicId
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
fun SelectMechanicForServiceFromList(mechanicList: List<MechanicModel>): Int {

    var selectedIndex = remember { mutableStateOf(-1) }

    val scroll = rememberScrollState()

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scroll)
    ) {
        mechanicList.forEachIndexed { index, mechanic ->

            var icon = R.drawable.deliveryboy

            Row(
                Modifier
                    .padding(15.dp, 7.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable {
                        selectedIndex.value = index
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
                        contentText = mechanic.userName,
                        size = 14.sp,
                        modifier = Modifier
                            .padding(10.dp, 0.dp)
                    )

                    Text(
                        text = "• " + mechanic.mechanicStatus,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        fontSize = 12.sp,
                        color = if (mechanic.mechanicStatus.equals("Available")) MaterialTheme.colorScheme.primary else if (mechanic.mechanicStatus.equals(
                                "On service"
                            )
                        ) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .padding(10.dp, 0.dp)
                    )
                }

                Icon(
                    painterResource(id = if (index.equals(selectedIndex.value)) R.drawable.check else R.drawable.oval),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }

    return selectedIndex.value

}