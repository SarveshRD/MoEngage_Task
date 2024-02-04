package com.wss.eat_space_iz.repository.loginSignUpTab


import com.wss.eat_space_iz.network.ApiService
import com.wss.eat_space_iz.repository.base.BaseRepo
import javax.inject.Inject

class ResetPasswordRepository
@Inject constructor(private val apiCall: ApiService) : BaseRepo() {

    /*suspend fun userPasswordReset(
        userResetPasswordRequest: UserResetPasswordRequest, onError: ((ApiResult<Any>) -> Unit)?,
    ): UserResetPasswordResponse? {
        return with(apiCall(executable = {
            apiCall.userPasswordReset(userResetPasswordRequest = userResetPasswordRequest)
        })) {
            if (data == null) onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }*/

}