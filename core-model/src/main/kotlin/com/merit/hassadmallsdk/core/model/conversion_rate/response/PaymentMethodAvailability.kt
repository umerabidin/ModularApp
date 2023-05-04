package com.merit.hassadmallsdk.core.model.conversion_rate.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentMethodAvailability(
    val applepay: Boolean,
    val credit_debit_cards: Boolean,
    val hassad_points: Boolean
):Parcelable