package com.wss.eat_space_iz.data.networkModels.tummoc


import com.google.gson.annotations.SerializedName

data class ShoppingResponse(
    @SerializedName("categories")
    val data: List<Category>,
    @SerializedName("error")
    val error: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)