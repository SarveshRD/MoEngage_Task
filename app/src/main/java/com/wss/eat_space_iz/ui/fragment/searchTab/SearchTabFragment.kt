package com.wss.eat_space_iz.ui.fragment.searchTab

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wss.eat_space_iz.data.networkModels.searchUser.Item
import com.wss.eat_space_iz.data.networkModels.searchUser.SearchUserResponse
import com.wss.eat_space_iz.databinding.CustomPopularNearYouListItemLayoutBinding
import com.wss.eat_space_iz.databinding.FragmentSearchTabBinding
import com.wss.eat_space_iz.ui.adapter.blogTab.followerAndFollowing.FollowerAdapter
import com.wss.eat_space_iz.ui.adapter.searchTab.ShowUserAdapter
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.searchTab.SearchTabViewModel
import com.wss.eat_space_iz.utils.Layouts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchTabFragment : BaseFrag<FragmentSearchTabBinding, SearchTabViewModel>(Layouts.fragment_search_tab)
{

    override val hasProgress: Boolean = false
    override fun getViewBinding() =  FragmentSearchTabBinding.inflate(layoutInflater)
    override val vm: SearchTabViewModel by viewModels()

    private lateinit var mUserAdapter: ShowUserAdapter
    private lateinit var mUserDataList: List<Item>

    override fun init() {
        setUpListener()
    }

    private fun setUpListener() {
        with(binding) {
            tvTrendingNearYou.setOnClickListener(this@SearchTabFragment)
        }
    }

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {
                when (apiRenderState.result) {
                    is SearchUserResponse -> {
                        val model = apiRenderState.result
                        //  model.status.let {
                        //  when (it) {
                        //  getString(Strings.api_success_status) -> {

                        if(model.totalCount == 0)
                        {
                            binding.tvRecentSearches.text = "Not found"

                        }else{
                            setupShowUserDataRecyclerView(model.items)

                        }

                        // }
                        //else -> {}
                        // }
                        //}
                    }
                }
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


    private fun setupShowUserDataRecyclerView(data: List<Item>) {
        mUserDataList = data
        if (mUserDataList.isNotEmpty()) {
            mUserAdapter = ShowUserAdapter(data)
            binding.rvSearchSuggestion.adapter = mUserAdapter

            mUserAdapter.setOnItemClickListener(object :
                ShowUserAdapter.OnItemClickListener {
                override fun onItemClick(position: Int, v: View) {
                    //openRestaurantDetails(position)
                    val action = SearchTabFragmentDirections.actionSearchTabFragmentToBlogsTabFragment(
                        userData = mUserDataList[position]
                    )
                    findNavController().navigate(action)

                }
            }
            )
        }
    }


    override fun onClick(v: View) {
        super.onClick(v)
        with(binding)
        {
            when (v)
            {
                tvTrendingNearYou -> {
                    val userName : String = etSearch.text.toString()

                   // vm.searchUser(userName)

                }
            }
        }
    }
}