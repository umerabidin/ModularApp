package com.merit.hassadmallsdk.core.network


data class HassadMallBaseResponse<T>(
    val code: Int=0,
    val data: T?,
    val errors: List<Any>?,
    val message: Any,
    val status: String,
    val total_records: Int? = 0,

)
