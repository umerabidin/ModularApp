package com.merit.hassadmallsdk.core.model.homebanner.response

data class HomepageBanner(
    val active: Boolean,
    val banner: Banner,
    val banner_arabic: BannerArabic,
    val banner_clickable_flag: Boolean,
    val id: Int,
    val product_type: String,
    val redirect_url: Any,
    val section_banners: Boolean,
    val third_section_banners: Boolean
)