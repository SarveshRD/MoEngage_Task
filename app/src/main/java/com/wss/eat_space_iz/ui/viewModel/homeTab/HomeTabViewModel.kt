package com.wss.eat_space_iz.ui.viewModel.homeTab


import com.wss.eat_space_iz.repository.homeTab.HomeTabRepository
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeTabViewModel
@Inject constructor(private val repo: HomeTabRepository) : BaseVM(){

    fun moEngage() {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.moEngage(onApiError)?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }


}