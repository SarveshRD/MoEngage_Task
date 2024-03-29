package com.wss.eat_space_iz.data.networkModels.searchUser


import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)