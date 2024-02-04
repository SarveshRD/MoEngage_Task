package com.wss.eat_space_iz.ui.fragment.profileTab


import android.view.View
import androidx.fragment.app.viewModels
import com.wss.eat_space_iz.databinding.FragmentAddAddressesBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.profileTab.AddAddressesViewModel
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.utils.Strings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAddressesFragment :
    BaseFrag<FragmentAddAddressesBinding, AddAddressesViewModel>(Layouts.fragment_add_addresses) {

    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentAddAddressesBinding.inflate(layoutInflater)
    override val vm: AddAddressesViewModel by viewModels()
    override fun init() {
        setUpToolbar()
        setUpListener()
    }

    private fun setUpListener() {

    }

    private fun setUpToolbar() {
        with(binding.myAddressesToolbar) {
            tvMyProfile.text = getText(Strings.mtb_profile_add_address_txt)
            imgBtnBack.setImageResource(Drawables.left_cancel_icon)
        }
    }

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {

            }
            is ApiRenderState.ValidationError -> {
                apiRenderState.message?.let {
                    errorToast(getString(it))
                }
            }
            is ApiRenderState.ApiError<*> -> {
                errorToast(apiRenderState.error.toString())
            }
            else -> {}
        }
    }

    override fun onClick(v: View) {
        super.onClick(v)
        with(binding) {
            when (v) {

            }
        }
    }
}