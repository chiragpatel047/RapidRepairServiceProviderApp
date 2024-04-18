package com.chirag047.rapidservice.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Model.Coordinates
import com.chirag047.rapidservice.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackNowScreenViewModel @Inject constructor(val dataRepository: DataRepository) :

    ViewModel() {

    private val _liveData = MutableStateFlow<ResponseType<Coordinates?>>(ResponseType.Loading())
    val liveData: StateFlow<ResponseType<Coordinates?>>
        get() = _liveData


    private val _doneData = MutableStateFlow<ResponseType<String>>(ResponseType.Loading())
    val doneData: StateFlow<ResponseType<String>>
        get() = _doneData

    suspend fun trackLiveLocation(orderId: String) {
        viewModelScope.launch {
            dataRepository.trackLiveLocation(orderId).collect {
                _liveData.emit(it)
            }
        }
    }


}