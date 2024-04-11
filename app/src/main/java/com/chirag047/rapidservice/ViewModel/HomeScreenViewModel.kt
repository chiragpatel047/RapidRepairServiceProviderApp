package com.chirag047.rapidservice.ViewModel

import androidx.lifecycle.ViewModel
import com.chirag047.rapidservice.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    suspend fun getPendingOrderRequests(centerId: String) =
        dataRepository.getPendingOrderRequests(centerId)

}