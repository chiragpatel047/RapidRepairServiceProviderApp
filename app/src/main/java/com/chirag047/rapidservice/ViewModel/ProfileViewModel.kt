package com.chirag047.rapidservice.ViewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.chirag047.rapidservice.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    suspend fun updateUserProfilePictureAndPhone(
        userImage: String,
        userName: String,
        phoneNo: String,
        sharedPreferences: SharedPreferences
    ) =
        dataRepository.updateUserProfilePictureAndPhone(
            userImage,
            userName,
            phoneNo,
            sharedPreferences
        )

    suspend fun getUserDetails() = dataRepository.getUserDetails()

    suspend fun getSingleCenterDetails(centerId: String) =
        dataRepository.getSingleCenterDetails(centerId)

    suspend fun updateCenterDetails(
        centerId: String,
        centerName: String,
        centerAddress: String,
        centerPhoneNo: String,
        centerTime: String
    ) =
        dataRepository.updateCenterDetails(
            centerId,
            centerName,
            centerAddress,
            centerPhoneNo,
            centerTime
        )
}