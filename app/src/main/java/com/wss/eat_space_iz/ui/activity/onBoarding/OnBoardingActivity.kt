package com.wss.eat_space_iz.ui.activity.onBoarding

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.wss.eat_space_iz.R
import com.wss.eat_space_iz.data.localModels.OnBoardingScreenData
import com.wss.eat_space_iz.databinding.ActivityOnBoardingBinding
import com.wss.eat_space_iz.ui.adapter.onBoarding.OnBoardingPagerAdapter
import com.wss.eat_space_iz.utils.sessionManagers.OnBoardingSessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private var onBoardingScreenDataList = ArrayList<OnBoardingScreenData>()

    @Inject
    lateinit var onBoardingSessionManager: OnBoardingSessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        view.hideSoftKeyboard()
        setUpViewPager()
    }

    private fun setUpViewPager() {
        onBoardingScreenDataList = getOnBoardingList()
        with(binding) {
           // vpOnBoarding.adapter = OnBoardingPagerAdapter(onBoardingScreenDataList)
            vpIndicator.attachTo(binding.vpOnBoarding)
        }
    }

    private fun getOnBoardingList(): ArrayList<OnBoardingScreenData> {
        val dataList = ArrayList<OnBoardingScreenData>()

        dataList.add(
            OnBoardingScreenData(
                R.drawable.eat_space_iz_on_boarding_logo,
                R.drawable.burger_icon,
                getString(R.string.onboarding_choose_a_favourite_food),
                getString(R.string.onboarding_first_discription),
                getString(R.string.onboarding_skip),
                false
            )
        )

        dataList.add(
            OnBoardingScreenData(
                R.drawable.eat_space_iz_on_boarding_logo,
                R.drawable.dish_icon,
                getString(R.string.onboarding_receive_the_great_food),
                getString(R.string.onboarding_second_discription),
                getString(R.string.onboarding_skip),
                false
            )
        )

        dataList.add(
            OnBoardingScreenData(
                R.drawable.eat_space_iz_on_boarding_logo,
                R.drawable.motor_bike_icon,
                getString(R.string.onboarding_hot_delivery_to_home),
                getString(R.string.onboarding_third_discription),
                getString(R.string.onboarding_skip),
                true
            )
        )

        return dataList
    }

    fun onBoardingStateChange() {
        //mOnBoardingSessionManager = OnBoardingSessionManager(this)
        onBoardingSessionManager.chooseAFavouriteFood = true
        onBoardingSessionManager.receiveTheGreatFood = true
        onBoardingSessionManager.hotDeliveryToHome = true
    }

    fun updateViewPagerPosition() {
        binding.vpOnBoarding.setCurrentItem(binding.vpOnBoarding.currentItem + 1, true)
    }

    fun View.hideSoftKeyboard() {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun View.showSoftKeyboard() {
        if (requestFocus())
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
                this,
                InputMethodManager.SHOW_IMPLICIT
            )
    }

}