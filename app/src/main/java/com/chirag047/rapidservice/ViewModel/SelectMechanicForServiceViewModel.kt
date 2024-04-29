package com.chirag047.rapidservice.ViewModel

import androidx.lifecycle.ViewModel
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Model.MechanicModel
import com.chirag047.rapidservice.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SelectMechanicForServiceViewModel @Inject constructor(val dataRepository: DataRepository) :
    ViewModel() {

    private val _mechanicData =
        MutableStateFlow<ResponseType<List<MechanicModel>>>(ResponseType.Loading())
    val mechanicData: StateFlow<ResponseType<List<MechanicModel>>>
        get() = _mechanicData

    suspend fun getMyAllMechanics(
        centerId: String
    ) {
        dataRepository.getMyAllMechanics(centerId).collect {
            _mechanicData.emit(it)
        }
    }

    suspend fun submitOrderToMechanic(
        orderId: String,
        mechanicId: String,
        userId: String,
        centerName : String
    ) = dataRepository.submitOrderToMechanic(orderId, mechanicId,userId,centerName)


}