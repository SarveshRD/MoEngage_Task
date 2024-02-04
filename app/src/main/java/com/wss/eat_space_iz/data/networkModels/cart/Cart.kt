package com.wss.eat_space_iz.data.networkModels.cart


import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("price")
    val price: Double
)