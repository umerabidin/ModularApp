package com.merit.hassadmallsdk.usecase.giftcard_unit_currencies

import com.merit.hassadmallsdk.R
import com.merit.hassadmallsdk.core.model.conversion_rate.request.ConversionRateRequestParam
import com.merit.hassadmallsdk.core.model.conversion_rate.response.GetConversionRateResponse
import com.merit.hassadmallsdk.core.model.giftcard_unit_currencies.response.GiftCardUnitCurrencyResponse
import com.merit.hassadmallsdk.core.network.repository.countries.GetHomeRepository
import com.merit.hassadmallsdk.usecase.BaseUseCaseWithParams
import com.merit.hassadmallsdk.usecase.BaseUseCaseWithoutParams
import com.merit.hassadmallsdk.utils.Resource
import retrofit2.Retrofit
import java.net.UnknownHostException
import javax.inject.Inject


class GetGiftCardUnitCurrencyUseCase @Inject constructor(
    val getCountriesRepository: GetHomeRepository,
    val retrofit: Retrofit
) : BaseUseCaseWithoutParams<Resource<Any>>() {


    override suspend fun run(): Resource<GiftCardUnitCurrencyResponse> {
        return try {
            val response = getCountriesRepository.getAllCurrencies()
            handleResponse(response, retrofit)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is UnknownHostException) {
                Resource.intError(R.string.err_no_connection, null)
            } else
                Resource.intError(R.string.err_generic, null)
        }  }
}