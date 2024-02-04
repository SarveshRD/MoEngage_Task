package com.wss.eat_space_iz.ui.fragment.homeTab


import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wss.eat_space_iz.data.networkModels.favitems.FavItemsResponse
import com.wss.eat_space_iz.data.networkModels.favitems.Favitem
import com.wss.eat_space_iz.data.networkModels.tummoc.Item
import com.wss.eat_space_iz.databinding.FragmentAllRestaurantListBinding
import com.wss.eat_space_iz.ui.adapter.profileTab.CategoriesAdapter
import com.wss.eat_space_iz.ui.adapter.profileTab.HistoryAdapter
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.homeTab.AllRestaurantListViewModel
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.utils.Strings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllRestaurantListFragment :
    BaseFrag<FragmentAllRestaurantListBinding, AllRestaurantListViewModel>(Layouts.fragment_all_restaurant_list) {


    private var mHistoryAdapter: HistoryAdapter? = null
    private var mHistoryDataList= ArrayList<Favitem>()

    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentAllRestaurantListBinding.inflate(layoutInflater)
    override val vm: AllRestaurantListViewModel by viewModels()

    override fun init() {
        setUpListener()
        tummoc()
    }

    private fun tummoc() {
       // vm.favitem()
    }

    private fun setUpListener() {
        with(binding) {
            ivBack.setOnClickListener(this@AllRestaurantListFragment)
        }
    }

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {
                when (apiRenderState.result) {
                    is FavItemsResponse -> {
                        val model = apiRenderState.result
                        model.status.let {
                          //  when (it) {
                              //  getString(Strings.api_success_status) -> {
                            val foodItemsList = ArrayList<Favitem>()
                            foodItemsList.addAll(model.data)
                            setupCategoriesRecyclerView( foodItemsList)

                              //  }
                          //  }
                        }
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

    override fun onClick(v: View) {
        super.onClick(v)
        with(binding)
        {
            when (v) {
                ivBack->{
                    findNavController().popBackStack()
                }
                else -> {}
            }
        }
    }

    private fun setupCategoriesRecyclerView(data: ArrayList<Favitem>) {
        mHistoryDataList = data
        if (mHistoryDataList.isNotEmpty()) {
            mHistoryAdapter = HistoryAdapter(data)
            binding.rvRestaurantsNearYou.adapter = mHistoryAdapter
        }
    }


}