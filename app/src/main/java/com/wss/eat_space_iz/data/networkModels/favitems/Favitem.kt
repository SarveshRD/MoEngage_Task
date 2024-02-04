package com.wss.eat_space_iz.data.networkModels.favitems


import com.google.gson.annotations.SerializedName

data class Favitem(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("unit")
    val unit: String
)