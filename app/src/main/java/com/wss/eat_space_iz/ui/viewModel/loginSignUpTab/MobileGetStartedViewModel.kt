package com.wss.eat_space_iz.ui.viewModel.loginSignUpTab

import com.wss.eat_space_iz.repository.loginSignUpTab.MobileGetStartedRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MobileGetStartedViewModel
@Inject constructor(private val repo: MobileGetStartedRepository) : BaseVM(){
}