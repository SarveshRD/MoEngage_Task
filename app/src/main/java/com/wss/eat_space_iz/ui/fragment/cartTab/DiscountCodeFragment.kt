package com.wss.eat_space_iz.ui.fragment.cartTab

import android.view.View
import androidx.fragment.app.viewModels
import com.wss.eat_space_iz.databinding.FragmentDiscountCodeBinding
import com.wss.eat_space_iz.ui.adapter.discountCode.DiscountCodeAdapter
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.cartTab.DiscountCodeViewModel
import com.wss.eat_space_iz.utils.Layouts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscountCodeFragment : BaseFrag<FragmentDiscountCodeBinding, DiscountCodeViewModel>(Layouts.fragment_discount_code)
{
    private lateinit var mDiscountCodeAdapter: DiscountCodeAdapter
    private lateinit var mDiscountCodeDataList : List<Any>
  //test
    override val hasProgress: Boolean = false
    override fun getViewBinding() =  FragmentDiscountCodeBinding.inflate(layoutInflater)
    override val vm: DiscountCodeViewModel by viewModels()

    override fun init() {
        setUpListener()
        viewAdminOffer()
    }
    private fun viewAdminOffer()
    {
      //  vm.viewAdminOffer()
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
                when (apiRenderState.result) {
                    /*is ViewAdminOffersResponse -> {
                        val model = apiRenderState.result
                        model.status?.let {
                            when (it) {
                                getString(Strings.api_success_status) -> {
                                    setupActiveCampaignRecyclerView(model.data)
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

    override fun onClick(v: View) {
        super.onClick(v)
        with(binding)
        {
            when (v)
            {

            }
        }
    }

    private fun setupActiveCampaignRecyclerView(data: List<Any>) {
        mDiscountCodeDataList = data
        if (mDiscountCodeDataList.isNotEmpty()) {
            mDiscountCodeAdapter = DiscountCodeAdapter(data)
            binding.rvDisountCode.adapter = mDiscountCodeAdapter
            /*mDiscountCodeAdapter.setOnItemClickListener(object :
                DiscountCodeAdapter.OnItemClickListener {
                override fun onItemClick(position: Int, v: View) {

                }
            })*/
        } else {
            with(binding) {
               // mcvNoCampaigns.visibility = View.VISIBLE
                rvDisountCode.visibility = View.GONE
                /*mcvCampaignStatusEmpty.visibility = View.VISIBLE
                mcvCampaignStatusNonEmpty.visibility = View.GONE*/
            }
        }
    }
}