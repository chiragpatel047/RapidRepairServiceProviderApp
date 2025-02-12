package com.chirag047.rapidservice.Api

import com.chirag047.rapidservice.Constant.ConstantValues.Companion.CONTENT_TYPE
import com.chirag047.rapidservice.Constant.ConstantValues.Companion.SERVER_KEY
import com.chirag047.rapidservice.Model.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {

    @Headers("Authorization: key=$SERVER_KEY","Content-Type: $CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(@Body notification: PushNotification): Response<ResponseBody>

}