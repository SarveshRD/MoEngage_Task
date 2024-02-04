package com.wss.eat_space_iz.ui.viewModel.homeTab


import com.wss.eat_space_iz.repository.homeTab.AllRestaurantListRepository
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllRestaurantListViewModel
@Inject constructor(private val repo: AllRestaurantListRepository) : BaseVM(){

  /*  fun favitem() {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.favitem(onApiError)?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }
*/

}