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

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.merit.hassadmallsdk.R
import com.merit.hassadmallsdk.databinding.ActivityMainBinding
import com.merit.hassadmallsdk.utils.Status
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    @get:VisibleForTesting
    internal val viewModel: HomeApiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding.btnGetCountries.setOnClickListener {
            viewModel.getAllCountries()
        }

        binding.btnGetConversionRate.setOnClickListener {
            viewModel.getConversionRate()
        }

        startObservings()

    }

    private fun startObservings() {

        viewModel.getConversionRateLiveData.observe(this) { event ->
            when (event.peakContent().status) {
                Status.SUCCESS -> {
                    event.peakContent().data?.let { response ->
                        binding.tvResponse.text = Gson().toJson(response.toString())
                    }
                }

                Status.ERROR -> {
                    if (event.peakContent().message is String) {
                    } else {
                    }
                }

                Status.LOADING -> {
                }

                Status.ERROR_INT -> {
                    event.peakContent().messageId?.let {
                    }
                }
            }


        }


        viewModel.getCountriesLiveData.observe(this) { event ->
            when (event.peakContent().status) {
                Status.SUCCESS -> {
                    event.peakContent().data?.let { response ->
                        binding.tvResponse.text = Gson().toJson(response.countries)
                    }
                }

                Status.ERROR -> {
                    if (event.peakContent().message is String) {
                    } else {
                    }
                }

                Status.LOADING -> {
                }

                Status.ERROR_INT -> {
                    event.peakContent().messageId?.let {
                    }
                }
            }

        }
    }
}
