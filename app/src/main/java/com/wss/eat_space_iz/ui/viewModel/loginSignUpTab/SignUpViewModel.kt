package com.wss.eat_space_iz.ui.viewModel.loginSignUpTab


import com.wss.eat_space_iz.repository.loginSignUpTab.SignUpRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject constructor(private val repo: SignUpRepository) : BaseVM(){

}