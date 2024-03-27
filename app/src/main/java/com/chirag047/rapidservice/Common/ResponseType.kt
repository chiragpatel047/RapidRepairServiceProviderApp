package com.chirag047.rapidservice.Common

sealed class ResponseType<T>(val data: T? = null, val errorMsg: String? = null) {


    class Success<T>(data: T) : ResponseType<T>(data = data)
    class Error<T>(errorMsg: String) : ResponseType<T>(errorMsg = errorMsg)

    class Loading<T>() : ResponseType<T>()

}