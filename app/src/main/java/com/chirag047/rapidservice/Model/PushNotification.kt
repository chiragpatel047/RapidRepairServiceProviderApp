package com.chirag047.rapidservice.Model

data class PushNotification(
    val data : FirebaseNotificationModel,
    val to : String
)
