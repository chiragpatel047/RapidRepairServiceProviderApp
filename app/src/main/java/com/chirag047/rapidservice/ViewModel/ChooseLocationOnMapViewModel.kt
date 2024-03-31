package com.chirag047.rapidservice.ViewModel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Model.CenterModel
import com.chirag047.rapidservice.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseLocationOnMapViewModel @Inject constructor(val dataRepository: DataRepository) :
    ViewModel() {

    suspend fun createNewCenter(centerModel: CenterModel) =
        dataRepository.createNewCenter(centerModel)
}