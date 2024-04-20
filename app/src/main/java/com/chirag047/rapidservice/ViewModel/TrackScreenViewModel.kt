package com.chirag047.rapidservice.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Model.OrderModel
import com.chirag047.rapidservice.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackScreenViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    val _requestsList = MutableStateFlow<ResponseType<List<OrderModel>>>(ResponseType.Loading())
    val requestsList: StateFlow<ResponseType<List<OrderModel>>>
        get() = _requestsList

    suspend fun getMyOrdersRequest(centerId: String) {
        viewModelScope.launch {
            dataRepository.getMyOrdersRequest(centerId).collect {
                _requestsList.emit(it)
            }
        }
    }

}