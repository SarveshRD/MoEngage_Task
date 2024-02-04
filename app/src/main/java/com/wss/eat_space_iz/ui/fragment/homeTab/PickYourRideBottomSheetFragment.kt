package com.wss.eat_space_iz.ui.fragment.homeTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wss.eat_space_iz.databinding.FragmentPickYourRideBottomSheetBinding
import com.wss.eat_space_iz.ui.adapter.profileTab.CategoriesAdapter
import com.wss.eat_space_iz.ui.adapter.profileTab.PickYourRideAdapter
import com.wss.eat_space_iz.ui.adapter.profileTab.PopularNearYouAdapter
import com.wss.eat_space_iz.ui.adapter.profileTab.TimeDisplayAdapter
import com.wss.eat_space_iz.ui.viewModel.homeTab.PickYourRideViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PickYourRideBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        const val PICK_YOUR_RIDE_BOTTOM_SHEET_DIALOG_TAG = "SearchingRideBottomSheetDialogFragment"
    }

    private lateinit var mTimeDisplayAdapter: TimeDisplayAdapter
    private lateinit var mTimeDisplayDataList: List<Any>


    private var _binding: FragmentPickYourRideBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val pickYourRideViewModel: PickYourRideViewModel by viewModels()

    private lateinit var mPickYourRideAdapter: PickYourRideAdapter
    private lateinit var mPickYourRideData: List<Any?>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPickYourRideBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // (dialog as? BottomSheetDialog)?.behavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        getPickYourRideStateFlow()
        getPickYourRide()
        setupTimeDisplayRecyclerView(listOf("abc","bcd","xyz","hjk","dfg","asdl"))

    }
    private fun setupTimeDisplayRecyclerView(data: List<Any>) {
        mTimeDisplayDataList = data
        if (mTimeDisplayDataList.isNotEmpty()) {
            //mTimeDisplayAdapter = TimeDisplayAdapter(data)
            binding.rvYourPickRide.adapter = mTimeDisplayAdapter
        }
    }
    private fun getPickYourRide() {
        val token =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6IjQ1Nzg0NTQ1IiwiZXhwIjoxNjQzNjk4OTgwLjB9.JXOaeECI5M46rB7G1K_PaYEge2LIv-UaUy4cKGZl2cM"
        //pickYourRideViewModel.pickYourRide(token)
    }

    private fun getPickYourRideStateFlow() {

      /*  lifecycleScope.launchWhenStarted {
            pickYourRideViewModel._pickYouRideStateFlow.collect {
                when (it) {
                    is ApiState.Loading -> {
                        // showProgress()
                        Log.d(AppConstants.App.ANDROID_LOG_D, "getUserRideHistory: loading")
                    }
                    is ApiState.Failure -> {
                        // hideProgress()
                        Log.d(AppConstants.App.ANDROID_LOG_D, "getUserRideHistory: ${it.msg}")
                    }
                    is ApiState.Success<*> -> {
                        // hideProgress()
                        when (it.data) {
                            is PickYourRideResponse -> {
                                if (it.data.data != null) {
                                    //setUpRecyclerView(it.data.data)
                                }
                            }

                        }
                    }
                    is ApiState.Empty -> {
                        Log.d(AppConstants.App.ANDROID_LOG_D, "getUserRideHistory: empty")
                    }
                }
            }
        }*/

    }

    /*private fun setUpRecyclerView(pickYourRideData: List<PickYourRideData?>) {
        with(binding) {
            mPickYourRideData = pickYourRideData
            mPickYourRideAdapter = PickYourRideAdapter(mPickYourRideData, mTotalDistance)
            rvYourPickRide.adapter = mPickYourRideAdapter
            mPickYourRideAdapter.setOnItemClickListener(object :
                PickYourRideAdapter.OnItemClickListener {
                override fun onItemClick(position: Int, v: View) {

                }
            })
        }

    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}