package com.wss.eat_space_iz.repository.profileTab


import com.wss.eat_space_iz.network.ApiService
import com.wss.eat_space_iz.repository.base.BaseRepo
import javax.inject.Inject

class MyAddressesRepository
@Inject constructor(private val apiCall: ApiService) : BaseRepo() {

    /*suspend fun viewUserAddress(
        userId: String, onError: ((ApiResult<Any>) -> Unit)?,
    ): ViewUserAddressResponse? {
        return with(apiCall(executable = {
            apiCall.viewUserAddress(userId = userId)
        })) {
            if (data == null) onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }*/
}