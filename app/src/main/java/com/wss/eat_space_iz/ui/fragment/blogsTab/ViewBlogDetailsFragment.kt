package com.wss.eat_space_iz.ui.fragment.blogsTab

import android.view.View
import androidx.fragment.app.viewModels
import com.wss.eat_space_iz.databinding.FragmentViewBlogDetailsBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.blogsTab.ViewBlogDetailsViewModel
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewBlogDetailsFragment : BaseFrag<FragmentViewBlogDetailsBinding, ViewBlogDetailsViewModel>(Layouts.fragment_view_blog_details)
{

    override val hasProgress: Boolean = false

    override fun getViewBinding() =  FragmentViewBlogDetailsBinding.inflate(layoutInflater)

    override val vm: ViewBlogDetailsViewModel by viewModels()

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
