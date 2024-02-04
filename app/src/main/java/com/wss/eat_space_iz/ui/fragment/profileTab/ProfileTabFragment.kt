package com.wss.eat_space_iz.ui.fragment.profileTab


import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.wss.eat_space_iz.databinding.FragmentProfileTabBinding
import com.wss.eat_space_iz.ui.activity.loginSignUp.LoginSignUpActivity
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.profileTab.ProfileTabViewModel
import com.wss.eat_space_iz.utils.Layouts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileTabFragment : BaseFrag<FragmentProfileTabBinding, ProfileTabViewModel>(Layouts.fragment_profile_tab)
{

    private var partnerId: String? = null
    private lateinit var navController: NavController

    override val hasProgress: Boolean = false
    override fun getViewBinding() =  FragmentProfileTabBinding.inflate(layoutInflater)
    override val vm: ProfileTabViewModel by viewModels()

    override fun init() {
        //partnerId = prefs.partnerId
        navController = findNavController()

        setUpListener()
    }

    private fun setUpListener() {
        with(binding) {

            civProfileImgEdit.setOnClickListener(this@ProfileTabFragment)
            vProfileMyOrders.setOnClickListener(this@ProfileTabFragment)
            vMyAddresses.setOnClickListener(this@ProfileTabFragment)
            vPaymentMethods.setOnClickListener(this@ProfileTabFragment)
            vOffersDeals.setOnClickListener(this@ProfileTabFragment)
            vFavourites.setOnClickListener(this@ProfileTabFragment)
            vHelpSupport.setOnClickListener(this@ProfileTabFragment)
            vTermsCondition.setOnClickListener(this@ProfileTabFragment)
            vChangePassword.setOnClickListener(this@ProfileTabFragment)
            vLogout.setOnClickListener(this@ProfileTabFragment)
            ivEditProfile.setOnClickListener(this@ProfileTabFragment)
            vOffersDeals.setOnClickListener(this@ProfileTabFragment)

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
        with(binding) {
            when (v) {
                vProfileMyOrders -> {
                    val action =
                        ProfileTabFragmentDirections.actionProfileTabFragmentToMyOrdersFragment()
                    navController.navigate(action)
                }
                vChangePassword -> {
                    /*val action =
                        ProfileTabFragmentDirections.actionProfileTabFragmentToChangePasswordFragment()
                    navController.navigate(action)*/
                }
                vFavourites -> {
                    val action =
                        ProfileTabFragmentDirections.actionProfileTabFragmentToMyFavouriteFragment()
                    navController.navigate(action)
                }
                vMyAddresses -> {
                    val action =
                        ProfileTabFragmentDirections.actionProfileTabFragmentToMyAddressesFragment()
                    navController.navigate(action)
                }
                civProfileImgEdit -> {
                    selectImage()
                }
                ivEditProfile -> {
                    val action =
                        ProfileTabFragmentDirections.actionProfileTabFragmentToProfileEditFragment()
                    navController.navigate(action)
                }
                vOffersDeals-> {
                    val action =
                        ProfileTabFragmentDirections.actionProfileTabFragmentToDiscountCodeFragment()
                    navController.navigate(action)
                }

                vLogout -> {
                   // logoutApp()
                }

                else -> {}
            }
        }
    }


    private fun logoutApp() {
        successToast("Logout App")
        Handler(Looper.getMainLooper()).postDelayed({
            prefs.clearPrefs()
            moveLoginIntent()
        }, 1000)
    }

    private fun moveLoginIntent() {
        val loginIntent = Intent(requireActivity(), LoginSignUpActivity::class.java)
        startActivity(loginIntent)
        requireActivity().finish()
    }


}