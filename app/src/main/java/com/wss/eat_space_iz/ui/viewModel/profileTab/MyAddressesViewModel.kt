package com.wss.eat_space_iz.ui.viewModel.profileTab

import com.wss.eat_space_iz.repository.profileTab.MyAddressesRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyAddressesViewModel
@Inject constructor(private val repo: MyAddressesRepository) : BaseVM() {

    /*fun viewUserAddress(userId: String) {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.viewUserAddress(
                userId = userId, onApiError
            )?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }*/
}