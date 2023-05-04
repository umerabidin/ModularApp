/*
 * Designed and developed by 2022 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.merit.hassadmallsdk.core.network.service

import com.merit.hassadmallsdk.core.model.CountriesListResponse
import com.merit.hassadmallsdk.core.model.conversion_rate.request.ConversionRateRequestParam
import com.merit.hassadmallsdk.core.model.conversion_rate.response.GetConversionRateResponse
import com.merit.hassadmallsdk.core.model.giftcard_unit_currencies.response.GiftCardUnitCurrencyResponse
import com.merit.hassadmallsdk.core.model.homebanner.response.HomeBannerResponse
import com.merit.hassadmallsdk.core.network.HassadMallBaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HassadMallServiceApi {
    @GET("countries")
    suspend fun getAllCountries(): Response<HassadMallBaseResponse<CountriesListResponse>>


    @POST("giftcard_units/get_currencies")
    suspend fun getAllCurrencies(): Response<HassadMallBaseResponse<GiftCardUnitCurrencyResponse>>


    @POST("giftcard_units/get_conversion_rate")
    suspend fun getCurrencyConversionRate(@Body params: ConversionRateRequestParam): Response<HassadMallBaseResponse<GetConversionRateResponse>>


    @GET("homepage_banners")
    suspend fun getHomeBanners(): Response<HassadMallBaseResponse<HomeBannerResponse>>


}
