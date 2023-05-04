package com.merit.hassadmallsdk.usecase

import com.merit.hassadmallsdk.core.network.HassadMallBaseResponse
import com.merit.hassadmallsdk.utils.Resource
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit

abstract class BaseUseCaseWithoutParams<R> {

    abstract suspend fun run(): R


    inline fun <reified T : HassadMallBaseResponse<R>, R> handleResponse(
        response: Response<HassadMallBaseResponse<R>>,
        retrofit: Retrofit
    ): Resource<R> {
        val converter: Converter<ResponseBody, Any> =
            retrofit.responseBodyConverter(T::class.java, arrayOfNulls<Annotation>(0))

        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it.data, it.message, it.total_records ?: 0)
                } ?: return Resource.error("Something Went Wrong body is null", null, (response.raw() as okhttp3.Response).code)

            } else {
                response.errorBody()?.let {
                    val error = converter.convert(it) as HassadMallBaseResponse<*>
                    return@let error.errors?.first()?.let {
                        return@let Resource.error(it.toString(), null, (response.raw() as okhttp3.Response).code)
                    } ?: return Resource.intError(com.merit.hassadmallsdk.R.string.err_generic, null, (response.raw() as okhttp3.Response).code)
                } ?: Resource.intError(com.merit.hassadmallsdk.R.string.err_generic, null, (response.raw() as okhttp3.Response).code)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.intError(com.merit.hassadmallsdk.R.string.err_generic, null, (response.raw() as okhttp3.Response).code)
        }

    }
}