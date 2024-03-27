package com.chirag047.rapidservice.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag047.rapidservice.Common.ResponseType
import com.chirag047.rapidservice.Repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(val authRepository: AuthRepository) :
    ViewModel() {
    private val _response = MutableStateFlow<ResponseType<String>>(ResponseType.Loading())
    val response: StateFlow<ResponseType<String>>
        get() = _response

    fun sendEmailPasswordResetLink(email: String) = authRepository.sendEmailPasswordResetLink(email)

}