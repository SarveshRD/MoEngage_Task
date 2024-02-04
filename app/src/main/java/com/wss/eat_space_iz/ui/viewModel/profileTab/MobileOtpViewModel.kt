package com.wss.eat_space_iz.ui.viewModel.profileTab


import com.wss.eat_space_iz.repository.profileTab.MobileOtpRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MobileOtpViewModel
@Inject constructor(private val repo: MobileOtpRepository) : BaseVM(){
}