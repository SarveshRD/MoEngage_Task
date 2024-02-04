package com.wss.eat_space_iz.ui.fragment.profileTab

import android.view.View
import androidx.fragment.app.viewModels
import com.wss.eat_space_iz.databinding.FragmentMyOrdersBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.profileTab.MyOrdersViewModel
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.utils.Strings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyOrdersFragment :
    BaseFrag<FragmentMyOrdersBinding, MyOrdersViewModel>(Layouts.fragment_my_orders) {

    override val hasProgress: Boolean = false

    override fun getViewBinding() = FragmentMyOrdersBinding.inflate(layoutInflater)

    override val vm: MyOrdersViewModel by viewModels()

    override fun init() {
        setUpToolbar()
        setUpListener()
    }

    private fun setUpListener() {
        with(binding) {

        }
    }

    private fun setUpToolbar() {
        with(binding.profileToolbar) {
            tvMyProfile.text = getText(Strings.profile_my_orders)
            imgBtnBack.setImageResource(Drawables.left_back_arrow_icon)
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
        with(binding)
        {
            when (v) {

            }
        }
    }
}