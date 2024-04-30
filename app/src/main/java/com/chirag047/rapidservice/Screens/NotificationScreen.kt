package com.chirag047.rapidservice.Screens

import android.content.SharedPreferences
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chirag047.rapidservice.Common.ActionBarWIthBack
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Model.NotificationModel
import com.chirag047.rapidservice.ViewModel.NotificationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationScreen(navController: NavController, sharedPreferences: SharedPreferences) {

    Box(Modifier.fillMaxSize()) {

        val scope = rememberCoroutineScope()
        val notificationViewModel: NotificationViewModel = hiltViewModel()

        val notificationList = remember {
            mutableListOf<NotificationModel>()
        }
        val notificationHeaderList = remember {
            mutableListOf<String>()
        }
        val notificationContentList = remember {
            mutableListOf<NotificationModel>()
        }

        Column(Modifier.fillMaxWidth()) {
            ActionBarWIthBack(title = "Notification")

            LaunchedEffect(key1 = Unit) {
                scope.launch(Dispatchers.Main) {
                    notificationViewModel.getMyAllNotifications().collect {
                        when (it) {
                            is ResponseType.Error -> {

                            }

                            is ResponseType.Loading -> {

                            }

                            is ResponseType.Success -> {
                                notificationList.addAll(it.data!!)
                                notificationList.forEach {
                                    notificationHeaderList.add(it.notificationDate)
                                }
                            }
                        }
                    }
                }
            }

            LazyColumn() {
                val dist = notificationHeaderList.distinct()

                dist.forEach { dateHeader ->

                    notificationList.forEach {
                        if (it.equals(dateHeader)) {
                            notificationContentList.add(it)
                        }
                    }

                    stickyHeader {
                        Text(text = dateHeader)
                    }

                    items(notificationContentList) {
                        Text(text = it.notificationTitle)
                    }
                }
            }
        }
    }
}