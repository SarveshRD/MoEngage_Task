package com.wss.eat_space_iz.ui.viewModel.loginSignUpTab


import com.wss.eat_space_iz.repository.loginSignUpTab.SignInRepository

import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject constructor(private val repo: SignInRepository) : BaseVM() {

    /*fun userEmailLogin(userLoginEmailRequest: UserLoginEmailRequest) {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.userEmailLogin(
                userLoginEmailRequest, onApiError
            )?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }

    fun userMobileNoLogin(userLoginMobileNoRequest: UserLoginMobileNoRequest) {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.userMobileNoLogin(
                userLoginMobileNoRequest, onApiError
            )?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }*/

}