package com.wss.eat_space_iz.ui.viewModel.blogsTab

import com.wss.eat_space_iz.repository.blogsTab.BlogTabRepository
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class BlogsTabViewModel
@Inject constructor(private val repo: BlogTabRepository) :BaseVM() {

  /*  fun callFollowers() {
        scope {
            state.emit(ApiRenderState.Loading)
            repo.callFollowers(onApiError)?.let {
                state.emit(ApiRenderState.ApiSuccess(it))
                return@scope
            }
        }
    }*/


}