package com.wss.eat_space_iz.repository.loginSignUpTab


import com.wss.eat_space_iz.network.ApiService
import com.wss.eat_space_iz.repository.base.ApiResult
import com.wss.eat_space_iz.repository.base.BaseRepo
import javax.inject.Inject

class OtpVerifyRepository
@Inject constructor(private val apiCall: ApiService) : BaseRepo() {

    /*suspend fun sendMobileOtp(
        sendMobileOtpRequest: SendMobileOtpRequest, onError: ((ApiResult<Any>) -> Unit)?,
    ): SendMobileOtpResponse? {
        return with(apiCall(executable = {
            apiCall.sendMobileOtp(sendMobileOtpRequest = sendMobileOtpRequest)
        })) {
            if (data == null) onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }

    suspend fun sendEmailOtp(
        sendEmailOtpRequest: SendEmailOtpRequest, onError: ((ApiResult<Any>) -> Unit)?,
    ): SendEmailOtpResponse? {
        return with(apiCall(executable = {
            apiCall.sendEmailOtp(sendEmailOtpRequest = sendEmailOtpRequest)
        })) {
            if (data == null) onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }

    suspend fun userSignUp(
        createUserAccountRequest: CreateUserAccountRequest, onError: ((ApiResult<Any>) -> Unit)?,
    ): CreateUserAccountResponse? {
        return with(apiCall(executable = {
            apiCall.userSignUp(createUserAccountRequest = createUserAccountRequest)
        })) {
            if (data == null) onError?.invoke(ApiResult(null, resultType, error, resCode = resCode))
            data
        }
    }
*/
}