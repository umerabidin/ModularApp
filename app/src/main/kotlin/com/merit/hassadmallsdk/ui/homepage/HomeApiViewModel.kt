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

package com.merit.hassadmallsdk.ui.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.merit.hassadmallsdk.core.model.CountriesListResponse
import com.merit.hassadmallsdk.core.model.conversion_rate.request.ConversionRateRequestParam
import com.merit.hassadmallsdk.core.model.conversion_rate.request.GiftCardUnit
import com.merit.hassadmallsdk.core.model.conversion_rate.response.GetConversionRateResponse
import com.merit.hassadmallsdk.usecase.conversion_rate.GetConversionRateUseCase
import com.merit.hassadmallsdk.utils.Event
import com.merit.hassadmallsdk.utils.Resource
import com.skydoves.bindables.BindingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeApiViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getConversionRateUseCase: GetConversionRateUseCase,
) :
    BindingViewModel() {


    val getCountriesLiveData = MutableLiveData<Event<Resource<CountriesListResponse>>>()
    fun getAllCountries() {
        getCountriesLiveData.value = Event(Resource.loading(null))
        viewModelScope.launch {
            getCountriesLiveData.value = Event(getAllCountriesUseCase.run())
        }
    }


    val getConversionRateLiveData = MutableLiveData<Event<Resource<GetConversionRateResponse>>>()
    fun getConversionRate() {
        getCountriesLiveData.value = Event(Resource.loading(null))
        viewModelScope.launch {
            getConversionRateLiveData.value = Event(
                getConversionRateUseCase.run(
                    ConversionRateRequestParam(GiftCardUnit(1))
                )
            )
        }
    }


}
