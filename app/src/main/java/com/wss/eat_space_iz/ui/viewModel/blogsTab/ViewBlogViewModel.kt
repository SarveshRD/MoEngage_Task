package com.wss.eat_space_iz.ui.viewModel.blogsTab


import com.wss.eat_space_iz.repository.blogsTab.ViewBlogRepository
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewBlogViewModel
@Inject constructor(private val repo: ViewBlogRepository) : BaseVM(){
}