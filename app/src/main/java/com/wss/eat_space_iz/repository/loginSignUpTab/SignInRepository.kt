package com.wss.eat_space_iz.repository.loginSignUpTab


import com.wss.eat_space_iz.network.ApiService
import com.wss.eat_space_iz.repository.base.BaseRepo
import javax.inject.Inject

class SignInRepository
@Inject constructor(private val apiCall: ApiService) : BaseRepo() {

   /* suspend fun userEmailLogin(
        userLoginEmailRequest: UserLoginEmailRequest, onError: ((ApiResult<Any>) -> Unit)?,
    ): UserLoginResponse? {
        return with(apiCall(executable = {
            apiCall.userEmailLogin(userLoginEmailRequest = userLoginEmailRequest)
        })) {
            if (data == null) onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }

    suspend fun userMobileNoLogin(
        userLoginMobileNoRequest: UserLoginMobileNoRequest, onError: ((ApiResult<Any>) -> Unit)?,
    ): UserLoginResponse? {
        return with(apiCall(executable = {
            apiCall.userMobileNoLogin(userLoginMobileNoRequest = userLoginMobileNoRequest)
        })) {
            if (data == null) onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }*/

}