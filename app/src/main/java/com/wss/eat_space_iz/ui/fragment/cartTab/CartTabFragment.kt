package com.wss.eat_space_iz.ui.fragment.cartTab

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wss.eat_space_iz.data.networkModels.cart.Cart
import com.wss.eat_space_iz.data.networkModels.cart.CartResponse
import com.wss.eat_space_iz.data.networkModels.favitems.FavItemsResponse
import com.wss.eat_space_iz.data.networkModels.favitems.Favitem
import com.wss.eat_space_iz.databinding.FragmentCartTabBinding
import com.wss.eat_space_iz.ui.adapter.profileTab.HistoryAdapter
import com.wss.eat_space_iz.ui.adapter.profileTab.TimeDisplayAdapter
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.cartTab.CartTabViewModel
import com.wss.eat_space_iz.utils.Layouts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartTabFragment : BaseFrag<FragmentCartTabBinding, CartTabViewModel>(Layouts.fragment_cart_tab) {

    override val hasProgress: Boolean = false
    override fun getViewBinding() =  FragmentCartTabBinding.inflate(layoutInflater)
    override val vm: CartTabViewModel by viewModels()

    private var mHistoryAdapter: TimeDisplayAdapter? = null
    private var mHistoryDataList= ArrayList<Cart>()

    override fun init() {
        setUpListener()
        tummoc()
    }

    private fun tummoc() {
      //  vm.cart()
    }

    private fun setUpListener() {
        with(binding) {
            ivCartLocation.setOnClickListener(this@CartTabFragment)
        }
    }

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {
                when (apiRenderState.result) {
                    is CartResponse -> {
                        val model = apiRenderState.result
                        model.status.let {
                            //  when (it) {
                            //  getString(Strings.api_success_status) -> {
                            val cartList = ArrayList<Cart>()
                            cartList.addAll(model.data)
                            setupCategoriesRecyclerView( cartList)

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
            when (v)
            {
                ivCartLocation ->{
                    findNavController().popBackStack()
                }
                else -> {}
            }
        }
    }

    private fun setupCategoriesRecyclerView(data: ArrayList<Cart>) {
        mHistoryDataList = data
        if (mHistoryDataList.isNotEmpty()) {
            mHistoryAdapter = TimeDisplayAdapter(data)
            binding.cartRecycle.adapter = mHistoryAdapter
        }
    }


}