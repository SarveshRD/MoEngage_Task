package com.wss.eat_space_iz.ui.fragment.profileTab

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wss.eat_space_iz.databinding.FragmentEditAddressesBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.profileTab.EditAddressesViewModel
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.utils.Strings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditAddressesFragment :
    BaseFrag<FragmentEditAddressesBinding, EditAddressesViewModel>(Layouts.fragment_edit_addresses) {

    override val hasProgress: Boolean = false

    override fun getViewBinding() = FragmentEditAddressesBinding.inflate(layoutInflater)

    override val vm: EditAddressesViewModel by viewModels()

    override fun init() {
        setUpToolbar()
        setUpListener()
    }

    private fun setUpListener() {
        with(binding) {
            myAddressesToolbar.imgBtnBack.setOnClickListener(this@EditAddressesFragment)
        }
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
                myAddressesToolbar.imgBtnBack -> {
                    findNavController().popBackStack()
                }
                else -> {}
            }
        }
    }
}