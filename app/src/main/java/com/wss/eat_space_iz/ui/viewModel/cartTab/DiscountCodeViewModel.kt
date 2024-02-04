package com.wss.eat_space_iz.ui.viewModel.cartTab


import com.wss.eat_space_iz.repository.cartTab.DiscountCodeRepository
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscountCodeViewModel
@Inject constructor(private val repo: DiscountCodeRepository) : BaseVM(){

    /*fun viewAdminOffer() {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.viewAdminOffer(onApiError)?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }*/



}