package com.wss.eat_space_iz.ui.viewModel.blogsTab


import com.wss.eat_space_iz.repository.blogsTab.ViewBlogDetailsRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewBlogDetailsViewModel
@Inject constructor(private val repo: ViewBlogDetailsRepository) : BaseVM(){
}