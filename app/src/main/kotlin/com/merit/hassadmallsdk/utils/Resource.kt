package com.merit.hassadmallsdk.utils

data class Resource<out T>(
    var status: Status,
    val data: T?,
    val message: Any? = "",
    val messageId: Int? = -1,
    val totalRecords: Int? = 0,
    val networkStatusCode: Int? = 0
) {
    companion object {
        fun <T> success(data: T?, message: Any? = "", totalRecords: Int? = 0): Resource<T> {
            return Resource(Status.SUCCESS, data, message, totalRecords = totalRecords)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> error(message: String, data: T?, errorCode: Int? = 0): Resource<T> {
            return Resource(Status.ERROR, data, message, networkStatusCode = errorCode)
        }

        fun <T> intError(message: Int, data: T?, errorCode: Int? = 0): Resource<T> {
            return Resource(Status.ERROR_INT, data, messageId = message, networkStatusCode = errorCode)
        }
    }
}

enum class Status {
    SUCCESS,
    LOADING,
    ERROR,
    ERROR_INT,
}