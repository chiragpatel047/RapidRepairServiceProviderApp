package com.chirag047.rapidservice.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Model.CenterModel
import com.chirag047.rapidservice.Model.MechanicModel
import com.chirag047.rapidservice.Model.OrderModel
import com.chirag047.rapidservice.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    private val _pendingList =
        MutableStateFlow<ResponseType<List<OrderModel>>>(ResponseType.Loading())
    val pendingList: StateFlow<ResponseType<List<OrderModel>>>
        get() = _pendingList

    private val _mechanicList =
        MutableStateFlow<ResponseType<List<MechanicModel>>>(ResponseType.Loading())
    val mechanicList: StateFlow<ResponseType<List<MechanicModel>>>
        get() = _mechanicList

    private val _centerDetails =
        MutableStateFlow<ResponseType<CenterModel?>>(ResponseType.Loading())
    val centerDetails: StateFlow<ResponseType<CenterModel?>>
        get() = _centerDetails

    private val _updateStatus =
        MutableStateFlow<ResponseType<String>>(ResponseType.Loading())
    val updateStatus: StateFlow<ResponseType<String>>
        get() = _updateStatus

    private val _declineOrder =
        MutableStateFlow<ResponseType<String>>(ResponseType.Loading())
    val declineOrder: StateFlow<ResponseType<String>>
        get() = _declineOrder


    suspend fun getPendingOrderRequests(centerId: String) {
        viewModelScope.launch {
            dataRepository.getPendingOrderRequests(centerId).collect {
                _pendingList.emit(it)
            }
        }
    }

    suspend fun getMyAllMechanics(
        centerId: String
    ) {
        viewModelScope.launch {
            dataRepository.getMyAllMechanics(centerId).collect {
                _mechanicList.emit(it)
            }
        }
    }

    suspend fun getSingleCenterDetails(centerId: String) {
        viewModelScope.launch {
            dataRepository.getSingleCenterDetails(centerId).collect {
                _centerDetails.emit(it)
            }
        }
    }


    suspend fun updateCenterStatus(centerId: String, centerStatus: String) {
        viewModelScope.launch {
            dataRepository.updateCenterStatus(centerId, centerStatus).collect {
                _updateStatus.emit(it)
            }
        }
    }

    suspend fun declineOrder(orderId: String) {
        viewModelScope.launch {
            dataRepository.declineOrder(orderId).collect {
                _declineOrder.emit(it)
            }
        }
    }
}