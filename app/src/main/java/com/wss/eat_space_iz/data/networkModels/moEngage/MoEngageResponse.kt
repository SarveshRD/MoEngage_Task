package com.wss.eat_space_iz.data.networkModels.moEngage


import com.google.gson.annotations.SerializedName

data class MoEngageResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String
)