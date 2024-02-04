package com.wss.eat_space_iz.ui.viewModel.loginSignUpTab


import com.wss.eat_space_iz.repository.loginSignUpTab.ResetPasswordRepository

import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel
@Inject constructor(private val repo: ResetPasswordRepository) : BaseVM() {

    /*fun userPasswordReset(userResetPasswordRequest: UserResetPasswordRequest) {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.userPasswordReset(
                userResetPasswordRequest = userResetPasswordRequest, onApiError
            )?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }*/

}