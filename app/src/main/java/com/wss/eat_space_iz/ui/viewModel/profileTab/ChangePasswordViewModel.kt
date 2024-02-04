package com.wss.eat_space_iz.ui.viewModel.profileTab


import com.wss.eat_space_iz.repository.profileTab.ChangePasswordRepository

import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel
@Inject constructor(private val repo: ChangePasswordRepository) : BaseVM() {

    /*fun changePassword(changePasswordRequest: ChangePasswordRequest) {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.changePassword(
                changePasswordRequest = changePasswordRequest, onApiError
            )?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }*/

}