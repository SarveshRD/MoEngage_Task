package com.wss.eat_space_iz.ui.viewModel.loginSignUpTab


import com.wss.eat_space_iz.repository.loginSignUpTab.OtpVerifyRepository

import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtpVerifyViewModel
@Inject constructor(private val repo: OtpVerifyRepository) : BaseVM() {

   /* fun sendMobileOtp(sendMobileOtpRequest: SendMobileOtpRequest) {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.sendMobileOtp(
                sendMobileOtpRequest, onApiError
            )?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }

    fun sendEmailOtp(sendEmailOtpRequest: SendEmailOtpRequest) {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.sendEmailOtp(
                sendEmailOtpRequest, onApiError
            )?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }

    fun userSignUp(createUserAccountRequest: CreateUserAccountRequest) {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.userSignUp(
                createUserAccountRequest, onApiError
            )?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }
*/
}