package com.gonexwind.ourstory.core.source.remote.response

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()

    data class Error(val errorMessage: String) : ApiResponse<Nothing>()

    object Loading : ApiResponse<Nothing>()
}