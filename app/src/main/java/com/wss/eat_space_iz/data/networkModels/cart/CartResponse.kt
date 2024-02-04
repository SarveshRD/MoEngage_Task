package com.wss.eat_space_iz.data.networkModels.cart


import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("cart")
    val data: List<Cart>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)