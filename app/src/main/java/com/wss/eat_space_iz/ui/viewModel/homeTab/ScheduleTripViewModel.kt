package com.wss.eat_space_iz.ui.viewModel.homeTab

import com.wss.eat_space_iz.repository.homeTab.HomeTabRepository
import com.wss.eat_space_iz.repository.homeTab.ScheduleTripRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScheduleTripViewModel
@Inject constructor(private val repo: ScheduleTripRepository) : BaseVM(){
}