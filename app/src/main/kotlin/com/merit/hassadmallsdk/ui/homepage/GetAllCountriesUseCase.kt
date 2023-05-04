package com.merit.hassadmallsdk.ui.homepage

import com.merit.hassadmallsdk.R
import com.merit.hassadmallsdk.core.model.CountriesListResponse
import com.merit.hassadmallsdk.core.network.repository.countries.GetHomeRepository
import com.merit.hassadmallsdk.usecase.BaseUseCaseWithoutParams
import com.merit.hassadmallsdk.utils.Resource
import retrofit2.Retrofit
import java.net.UnknownHostException
import javax.inject.Inject


class GetAllCountriesUseCase @Inject constructor(
    val getCountriesRepository: GetHomeRepository,
    val retrofit: Retrofit
) : BaseUseCaseWithoutParams<Resource<Any>>() {
    override suspend fun run(): Resource<CountriesListResponse> {

        return try {
            val response = getCountriesRepository.getAllCountries()
            handleResponse(response, retrofit)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is UnknownHostException) {
                Resource.intError(R.string.err_no_connection, null)
            } else
                Resource.intError(R.string.err_generic, null)
        }
    }
}