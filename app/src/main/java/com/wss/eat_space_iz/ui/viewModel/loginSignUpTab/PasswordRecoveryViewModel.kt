package com.wss.eat_space_iz.ui.viewModel.loginSignUpTab


import com.wss.eat_space_iz.repository.loginSignUpTab.PasswordRecoveryRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PasswordRecoveryViewModel
@Inject constructor(private val repo: PasswordRecoveryRepository) : BaseVM(){
}