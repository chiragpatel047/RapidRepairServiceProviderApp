package com.chirag047.rapidservice.ViewModel

import android.view.View
import androidx.lifecycle.ViewModel
import com.chirag047.rapidservice.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MechanicScreenViewModel @Inject constructor(val dataRepository: DataRepository) :
    ViewModel() {
    suspend fun getMyAllMechanics(
        centerId: String
    ) = dataRepository.getMyAllMechanics(centerId)

}