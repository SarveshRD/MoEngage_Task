package com.wss.eat_space_iz.data.networkModels.favitems


import com.google.gson.annotations.SerializedName

data class FavItemsResponse(
    @SerializedName("favitems")
    val data: List<Favitem>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)