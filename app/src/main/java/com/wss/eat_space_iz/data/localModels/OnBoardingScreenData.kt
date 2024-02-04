package com.wss.eat_space_iz.data.localModels

import androidx.annotation.DrawableRes

data class OnBoardingScreenData(
    @DrawableRes val OnBoardingLogo: Int,
    @DrawableRes val onBoardingImg: Int,
    val tagLine: String,
    val descText: String,
    val skipText: String,
    val is_get_started: Boolean
)