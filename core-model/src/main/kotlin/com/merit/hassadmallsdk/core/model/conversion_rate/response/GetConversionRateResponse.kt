package com.merit.hassadmallsdk.core.model.conversion_rate.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetConversionRateResponse(
    val currency_code: String,
    val currency_exchange_rate: Double,
    val enable_goal_item_feature: Boolean,
    val enable_mastercard_3ds2: Boolean,
    val other_markup_rate_for_topup: Double,
    val payment_method_availability: PaymentMethodAvailability,
    val point_conversion_rate: Double,
    val sar_markup_rate_for_topup: Double,
    val software_and_game_markup_rate: Double
) : Parcelable