package com.wss.eat_space_iz.ui.viewModel.cartTab


import com.wss.eat_space_iz.repository.cartTab.CartTabRepository
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartTabViewModel
@Inject constructor(private val repo: CartTabRepository) : BaseVM(){

  /*  fun cart() {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.cart(onApiError)?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }*/


}