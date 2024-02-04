package com.wss.eat_space_iz.ui.viewModel.homeTab

import com.wss.eat_space_iz.repository.homeTab.PickYourRideRepository
import com.wss.eat_space_iz.repository.homeTab.ScheduleTripRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PickYourRideViewModel
@Inject constructor(private val repo: PickYourRideRepository) : BaseVM(){
}