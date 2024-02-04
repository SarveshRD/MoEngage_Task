package com.wss.eat_space_iz.ui.fragment.blogsTab

import android.graphics.Typeface
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wss.eat_space_iz.R
import com.wss.eat_space_iz.data.networkModels.speer.FollowersResponce
import com.wss.eat_space_iz.data.networkModels.speer.FollowersResponceItem
import com.wss.eat_space_iz.data.networkModels.speer.FollowingResponse
import com.wss.eat_space_iz.data.networkModels.speer.FollowingResponseItem
import com.wss.eat_space_iz.databinding.FragmentFollowerAndFollowingBinding
import com.wss.eat_space_iz.ui.adapter.blogTab.followerAndFollowing.FollowerAdapter
import com.wss.eat_space_iz.ui.adapter.blogTab.followerAndFollowing.FollowingAdapter

import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.blogsTab.FollowerAndFollowingViewModel
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.utils.Strings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerAndFollowingFragment : BaseFrag<FragmentFollowerAndFollowingBinding,
        FollowerAndFollowingViewModel>(Layouts.fragment_follower_and_following) {

    private val args: FollowerAndFollowingFragmentArgs by navArgs()
    private var isFollower: Boolean = false

    private lateinit var mFollowerAdapter: FollowerAdapter
    private lateinit var mFollowerDataList: List<FollowersResponceItem>

    private lateinit var mFollowingAdapter: FollowingAdapter
    private lateinit var mFollowingDataList: List<FollowingResponseItem>

    private var mUserId: String? = null

    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentFollowerAndFollowingBinding.inflate(layoutInflater)
    override val vm: FollowerAndFollowingViewModel by viewModels()

    override fun init() {
        setUpListener()
        isFollower = args.isFollower
        mUserId = "2"    //prefs.userId
        mUserId?.let { viewFollowAndFollowing(it) }
        showFollowers()
    }

    private fun showFollowers() {
       /* vm.callFollowers()
        vm.callFollowing()*/
    }

    private fun setUpListener() {
        with(binding) {
            tvFollowers.setOnClickListener(this@FollowerAndFollowingFragment)
            tvFollowing.setOnClickListener(this@FollowerAndFollowingFragment)
            mtbFollowersFollowing.imgBtnBack.setOnClickListener(this@FollowerAndFollowingFragment)
        }
    }

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {

                when (apiRenderState.result) {
                    is FollowersResponce -> {
                        val model = apiRenderState.result
                        //  model.status.let {
                        //  when (it) {
                        //  getString(Strings.api_success_status) -> {

                        setupMyFollowersRecyclerView(model)


                        // }
                        //else -> {}
                        // }
                        //}
                    }
                    is FollowingResponse -> {
                        val model = apiRenderState.result
                        //  model.status.let {
                        //  when (it) {
                        //  getString(Strings.api_success_status) -> {

                        setupMyFollowingsRecyclerView(model)


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


    private fun setupMyFollowersRecyclerView(data: List<FollowersResponceItem>) {
        mFollowerDataList = data
        if (mFollowerDataList.isNotEmpty()) {
            mFollowerAdapter = FollowerAdapter(data)
            binding.rvFollowers.adapter = mFollowerAdapter
        }
    }

    private fun setupMyFollowingsRecyclerView(data: List<FollowingResponseItem>) {
        mFollowingDataList = data
        if (mFollowingDataList.isNotEmpty()) {
            mFollowingAdapter = FollowingAdapter(data)
            binding.rvFollowing.adapter = mFollowingAdapter
        }
    }

    private fun viewFollowAndFollowing(userId: String) {
        //vm.viewFollowingAndFollowers(userId)
    }


    override fun onClick(v: View) {
        super.onClick(v)
        with(binding) {
            when (v) {
                tvFollowers -> {
                    isFollower = true
                    switchFollowers()
                }
                tvFollowing -> {
                    isFollower = false
                    switchFollowing()
                }
                mtbFollowersFollowing.imgBtnBack -> {
                    findNavController().popBackStack()
                }
                else -> {}
            }
        }
    }


    private fun switchFollowers() {
        with(binding) {
            //   isFollower = true
            rvFollowing.visibility = View.GONE
            rvFollowers.visibility = View.VISIBLE
            tvFollowers.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.tv_follow_type_bg)
            tvFollowing.background = null
            val typeface: Typeface? = ResourcesCompat.getFont(requireContext(), R.font.gilroy_bold)
            tvFollowing.typeface = typeface
            setupMyFollowersRecyclerView(mFollowerDataList)
            //mUserId?.let { viewFollowAndFollowing(userId = it) }
        }
    }

    private fun switchFollowing() {
        with(binding) {
            //isFollower = false
            rvFollowers.visibility = View.GONE
            rvFollowing.visibility = View.VISIBLE
            tvFollowing.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.tv_follow_type_bg)
            tvFollowers.background = null
            val typeface: Typeface? = ResourcesCompat.getFont(requireContext(), R.font.gilroy_bold)
            tvFollowing.typeface = typeface
            setupMyFollowingsRecyclerView(mFollowingDataList)
            //mUserId?.let { viewFollowAndFollowing(userId = it) }
        }
    }


}
