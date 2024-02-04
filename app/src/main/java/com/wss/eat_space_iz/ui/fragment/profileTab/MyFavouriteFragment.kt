package com.wss.eat_space_iz.ui.fragment.profileTab

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wss.eat_space_iz.databinding.FragmentMyFavouriteBinding
import com.wss.eat_space_iz.ui.adapter.profileTab.favourite.MyFavouriteAdapter
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.profileTab.MyFavouriteViewModel
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.utils.Strings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFavouriteFragment :
    BaseFrag<FragmentMyFavouriteBinding, MyFavouriteViewModel>(Layouts.fragment_my_favourite) {

    private var mUserId: String? = null
    private lateinit var mMyFavouriteAdapter: MyFavouriteAdapter
    private lateinit var mMyFavouriteDataList: List<Any>

    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentMyFavouriteBinding.inflate(layoutInflater)
    override val vm: MyFavouriteViewModel by viewModels()

    override fun init() {
        setUpToolbar()
        setUpListener()
        mUserId = "71"    //prefs.userId
        mUserId?.let { myFavourite(it) }

    }

    private fun setUpListener() {
        with(binding) {
            myFavouritesToolbar.imgBtnBack.setOnClickListener(this@MyFavouriteFragment)
        }
    }

    private fun setUpToolbar() {
        with(binding.myFavouritesToolbar) {
            tvMyProfile.text = getText(Strings.mtb_profile_my_favourites_txt)
            imgBtnBack.setImageResource(Drawables.left_back_arrow_icon)
        }
    }

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {
                when (apiRenderState.result) {
                    /*is ViewFavouriteResponse -> {
                        val model = apiRenderState.result
                        model.status.let {
                            when (it) {
                                getString(Strings.api_success_status) -> {
                                    setupMyFavouriteRecyclerView(model.data.favourite)
                                }
                            }
                        }
                    }*/
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


    private fun setupMyFavouriteRecyclerView(data: List<Any>) {
        mMyFavouriteDataList = data
        if (mMyFavouriteDataList.isNotEmpty()) {
            mMyFavouriteAdapter = MyFavouriteAdapter(data)
            binding.rvPopularNearYou.adapter = mMyFavouriteAdapter
        } else {
            with(binding) {
                groupNoFavouritesFound.visibility = View.VISIBLE
                rvPopularNearYou.visibility = View.GONE
            }
        }

    }

    private fun myFavourite(userId: String) {
       // vm.viewMyFavourite(userId)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        with(binding) {
            when (v) {
                myFavouritesToolbar.imgBtnBack -> {
                    findNavController().popBackStack()
                }
                else -> {}
            }
        }
    }
}