package com.wss.eat_space_iz.ui.fragment.homeTab

import android.view.View
import androidx.fragment.app.viewModels
import com.wss.eat_space_iz.databinding.FragmentScheduleTripBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.homeTab.ScheduleTripViewModel
import com.wss.eat_space_iz.utils.Layouts
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScheduleTripFragment :
    BaseFrag<FragmentScheduleTripBinding, ScheduleTripViewModel>(Layouts.fragment_schedule_trip) {

    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentScheduleTripBinding.inflate(layoutInflater)
    override val vm: ScheduleTripViewModel by viewModels()

    override fun init() {
        setUpListener()
    }

    private fun setUpListener() {
        with(binding) {
            scheduleTripButton.setOnClickListener(this@ScheduleTripFragment)
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
            when (v) {
                scheduleTripButton -> {
                    showPickYourRideDialogFrag()
                }
            }
        }
    }

    fun showPickYourRideDialogFrag() {
        val pickYourRideBottomSheet = PickYourRideBottomSheetFragment()
        pickYourRideBottomSheet.show(
            childFragmentManager,
            PickYourRideBottomSheetFragment.PICK_YOUR_RIDE_BOTTOM_SHEET_DIALOG_TAG
        )
    }
}