package com.wss.eat_space_iz.ui.viewModel.profileTab


import com.wss.eat_space_iz.repository.profileTab.MyFavouriteRepository
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyFavouriteViewModel
@Inject constructor(private val repo: MyFavouriteRepository) : BaseVM(){

    /*fun viewMyFavourite(userId : String) {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.viewMyFavourite(userId,onApiError)?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }*/


}