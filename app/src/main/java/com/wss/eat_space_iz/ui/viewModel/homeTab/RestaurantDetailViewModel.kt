package com.wss.eat_space_iz.ui.viewModel.homeTab


import com.wss.eat_space_iz.repository.homeTab.RestaurantDetailRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel
@Inject constructor(private val repo: RestaurantDetailRepository) : BaseVM(){
}