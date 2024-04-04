package com.chirag047.rapidservice.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Repository.AuthRepository
import com.chirag047.rapidservice.Repository.DataRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val authRepository: AuthRepository,val dataRepository: DataRepository) : ViewModel() {

    suspend fun loginUser(email: String, password: String) = authRepository.loginUser(email, password)
    suspend fun getSingleCenterDetails(centerId : String) = dataRepository.getSingleCenterDetails(centerId)


}