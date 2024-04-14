package com.chirag047.rapidservice.ViewModel

import androidx.lifecycle.ViewModel
import com.chirag047.rapidservice.Repository.DataRepository
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddMechanicViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {
    suspend fun searchMechanic(mechanicId: String) = dataRepository.searchMechanic(mechanicId)
    suspend fun addNewMechanic(
        mechanicUid: String,
        centerId: String,
        centerName: String
    ) = dataRepository.addNewMechanic(mechanicUid, centerId, centerName)
}