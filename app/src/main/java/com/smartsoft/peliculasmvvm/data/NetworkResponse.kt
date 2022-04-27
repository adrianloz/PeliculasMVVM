package com.smartsoft.peliculasmvvm.data

class NetworkResponse<T>(val status: Int, val data: T?, val errorMessage: String?) {
    companion object {
        const val SUCCESS = 1
        const val FAILURE = 0
        fun <T> success(data: T?): NetworkResponse<T?>? {
            return NetworkResponse(SUCCESS, data, null)
        }
        fun <T> error(data: T?, errorMessage: String?): NetworkResponse<T?>? {
            return NetworkResponse(FAILURE, data, errorMessage)
        }
    }
}