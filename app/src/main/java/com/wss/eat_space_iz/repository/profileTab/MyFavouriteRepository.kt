package com.wss.eat_space_iz.repository.profileTab


import com.wss.eat_space_iz.network.ApiService
import com.wss.eat_space_iz.repository.base.BaseRepo
import javax.inject.Inject

class MyFavouriteRepository
@Inject constructor(private val apiCall: ApiService): BaseRepo() {

   /* suspend fun viewMyFavourite(
        userId : String,
        onError: ((ApiResult<Any>) -> Unit)?,
    ): ViewFavouriteResponse? {
        return with(apiCall(executable = {
            apiCall.viewFavourite(userId = userId)
        })) {
            if (data == null)
                onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }*/


}