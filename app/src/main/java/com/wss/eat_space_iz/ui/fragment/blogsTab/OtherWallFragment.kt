package com.wss.eat_space_iz.ui.fragment.blogsTab

import android.view.View
import androidx.fragment.app.viewModels
import com.wss.eat_space_iz.databinding.FragmentOtherWallBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.blogsTab.OtherWallViewModel
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherWallFragment : BaseFrag<FragmentOtherWallBinding, OtherWallViewModel>(Layouts.fragment_other_wall)
{
    override val hasProgress: Boolean = false

    override fun getViewBinding() =  FragmentOtherWallBinding.inflate(layoutInflater)

    override val vm: OtherWallViewModel by viewModels()

    override fun init() {
        setUpListener()
    }

    private fun setUpListener() {
        with(binding) {

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
            when (v)
            {

            }
        }
    }
}

