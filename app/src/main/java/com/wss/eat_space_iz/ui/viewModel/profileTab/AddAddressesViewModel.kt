package com.wss.eat_space_iz.ui.viewModel.profileTab


import com.wss.eat_space_iz.repository.profileTab.AddAddressesRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddAddressesViewModel
@Inject constructor(private val repo: AddAddressesRepository) : BaseVM(){
}