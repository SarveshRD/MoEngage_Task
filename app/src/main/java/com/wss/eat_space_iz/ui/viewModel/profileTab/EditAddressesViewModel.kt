package com.wss.eat_space_iz.ui.viewModel.profileTab


import com.wss.eat_space_iz.repository.profileTab.EditAddressesRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditAddressesViewModel
@Inject constructor(private val repo: EditAddressesRepository) : BaseVM(){
}