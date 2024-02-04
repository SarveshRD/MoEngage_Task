package com.wss.eat_space_iz.ui.viewModel.cartTab


import com.wss.eat_space_iz.repository.cartTab.AddressRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddressViewModel
@Inject constructor(private val repo: AddressRepository) : BaseVM(){
}