package com.wss.eat_space_iz.repository.homeTab


import com.wss.eat_space_iz.data.networkModels.moEngage.MoEngageResponse
import com.wss.eat_space_iz.data.networkModels.tummoc.ShoppingResponse
import com.wss.eat_space_iz.network.ApiService
import com.wss.eat_space_iz.repository.base.ApiResult
import com.wss.eat_space_iz.repository.base.BaseRepo
import javax.inject.Inject

class HomeTabRepository
@Inject constructor(private val apiCall: ApiService): BaseRepo(){

    suspend fun moEngage(
        onError: ((ApiResult<Any>) -> Unit)?,
    ): MoEngageResponse? {
        return with(apiCall(executable = {
            apiCall.moEngage()
        })) {
            if (data == null)
                onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }



}