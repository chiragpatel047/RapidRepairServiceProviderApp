package com.chirag047.rapidservice.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Model.NotificationModel
import com.chirag047.rapidservice.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    private val _requestsData =
        MutableStateFlow<ResponseType<List<NotificationModel>?>>(ResponseType.Loading())
    val requestsData: StateFlow<ResponseType<List<NotificationModel>?>>
        get() = _requestsData

    suspend fun getMyAllNotifications(centerId : String) {
        viewModelScope.launch {
            dataRepository.getMyAllNotifications(centerId).collect {
                _requestsData.emit(it)
            }
        }
    }
}