package com.wss.eat_space_iz.repository.profileTab


import com.wss.eat_space_iz.network.ApiService
import com.wss.eat_space_iz.repository.base.BaseRepo
import javax.inject.Inject

class ChangePasswordRepository
@Inject constructor(private val apiCall: ApiService) : BaseRepo() {

    /*suspend fun changePassword(
        changePasswordRequest: ChangePasswordRequest, onError: ((ApiResult<Any>) -> Unit)?,
    ): ChangePasswordResponse? {
        return with(apiCall(executable = {
            apiCall.changePassword(changePasswordRequest = changePasswordRequest)
        })) {
            if (data == null) onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }*/
}