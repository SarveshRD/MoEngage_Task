package com.wss.eat_space_iz.data.networkModels.tummoc


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("name")
    val name: String
)