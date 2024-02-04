package com.wss.eat_space_iz.ui.adapter.onBoarding

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wss.eat_space_iz.databinding.CustomOnBoardingItemLayoutBinding
import com.wss.eat_space_iz.ui.activity.loginSignUp.LoginSignUpActivity
import com.wss.eat_space_iz.ui.activity.onBoarding.OnBoardingActivity


class OnBoardingPagerAdapter(private val onBoardingScreenDataList: ArrayList<Any>) :
    RecyclerView.Adapter<OnBoardingPagerAdapter.MyViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val binding = CustomOnBoardingItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = onBoardingScreenDataList[position]
        with(holder.itemBinding) {
            if (!data.equals("")) {
                btnGetStarted.visibility = View.GONE
                groupSkipNext.visibility = View.VISIBLE
               /* ivOnboardingLogo.setImageResource(data.OnBoardingLogo)
                ivOnBoardingMain.setImageResource(data.onBoardingImg)
                tvTagline.text = data.tagLine
                tvOnBoardingDescription.text = data.descText
                tvSkip.text = data.skipText*/

            } else {
                btnGetStarted.visibility = View.VISIBLE
                groupSkipNext.visibility = View.GONE
              //  ivOnboardingLogo.setImageResource(data.OnBoardingLogo)
              //  ivOnBoardingMain.setImageResource(data.onBoardingImg)
              //  tvTagline.text = data.tagLine
              //  tvOnBoardingDescription.text = data.descText
              //  tvSkip.text = data.skipText
            }
            tvSkip.setOnClickListener {
                (context as OnBoardingActivity).onBoardingStateChange()
                loadLoginSignUpActivity()
            }
            btnGetStarted.setOnClickListener {
                (context as OnBoardingActivity).onBoardingStateChange()
                loadLoginSignUpActivity()
            }
            btnNext.setOnClickListener {
                (context as OnBoardingActivity).updateViewPagerPosition()
            }
        }

    }

    private fun loadLoginSignUpActivity() {
        val intent = Intent(context, LoginSignUpActivity::class.java)
        context.startActivity(intent)
        (context as OnBoardingActivity).finish()
    }

    override fun getItemCount(): Int = onBoardingScreenDataList.size

    class MyViewHolder(itemBinding: CustomOnBoardingItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val itemBinding = itemBinding
    }
}