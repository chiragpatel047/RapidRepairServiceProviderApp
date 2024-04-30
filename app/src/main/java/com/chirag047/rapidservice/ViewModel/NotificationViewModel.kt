package com.chirag047.rapidservice.ViewModel

import androidx.lifecycle.ViewModel
import com.chirag047.rapidservice.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel(){
    suspend fun getMyAllNotifications() = dataRepository.getMyAllNotifications()
}