package com.wss.eat_space_iz.repository.searchTab

import com.wss.eat_space_iz.data.networkModels.searchUser.SearchUserResponse
import com.wss.eat_space_iz.network.ApiService
import com.wss.eat_space_iz.repository.base.ApiResult
import com.wss.eat_space_iz.repository.base.BaseRepo
import javax.inject.Inject

class SearchTabRepository
@Inject constructor(private val apiCall: ApiService): BaseRepo() {

    /*suspend fun searchUser(
        userName : String,
        onError: ((ApiResult<Any>) -> Unit)?,
    ): SearchUserResponse? {
        return with(apiCall(executable = {
            apiCall.searchUser(userName =userName)
        })) {
            if (data == null)
                onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }*/


}