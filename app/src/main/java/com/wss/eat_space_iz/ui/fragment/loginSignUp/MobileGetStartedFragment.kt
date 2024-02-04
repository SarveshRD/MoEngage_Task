package com.wss.eat_space_iz.ui.fragment.loginSignUp

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wss.eat_space_iz.databinding.FragmentMobileGetStartedBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.loginSignUpTab.MobileGetStartedViewModel
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.utils.ValidationUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MobileGetStartedFragment :
    BaseFrag<FragmentMobileGetStartedBinding, MobileGetStartedViewModel>(Layouts.fragment_mobile_get_started) {

    private var mPlus: String = "+"
    private var mCountryCode: String? = null
    private var mUserMobile: String? = null
    private var mUserFullName: String? = null
    private var mUserEmail: String? = null
    private var mUserPassword: String? = null
    private var isMobile: Boolean = false
    private var isEmail: Boolean = false
    private val args: MobileGetStartedFragmentArgs by navArgs()
    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentMobileGetStartedBinding.inflate(layoutInflater)
    override val vm: MobileGetStartedViewModel by viewModels()

    override fun init() {
        setUpListener()
        setUpArguments()
        if (mCountryCode.isNullOrEmpty()) {
            with(binding) {
                ccpCountryCode.defaultCountryCode
                mCountryCode = mPlus + ccpCountryCode.defaultCountryCode
            }
        } else {
            with(binding) {
                ccpCountryCode.setCountryForPhoneCode(mCountryCode!!.toInt())
                mCountryCode = mPlus + ccpCountryCode.selectedCountryCode
            }
        }

    }

    private fun setUpArguments() {
        isEmail = args.isEmail
        isMobile = args.isMobile
        args.name?.let { name ->
            mUserFullName = name
        }
        args.email?.let { email ->
            mUserEmail = email
        }
        args.countryCode?.let { countryCode ->
            mCountryCode = countryCode
        }
        args.mobileNo?.let { mobileNo ->
            mUserMobile = mobileNo
        }
        args.password?.let { userPassword ->
            mUserPassword = userPassword
        }
    }

    private fun setUpListener() {
        with(binding) {
            btnNext.setOnClickListener(this@MobileGetStartedFragment)
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
                btnNext -> {
                    mCountryCode = mPlus + binding.ccpCountryCode.selectedCountryCode
                    mUserMobile = tietMobileNumber.text.toString().trim()
                    when {
                        ValidationUtil.isValidMobile(context = requireContext(),
                            view = binding.root,
                            mobile = mUserMobile) -> {
                            val action =
                                MobileGetStartedFragmentDirections.actionMobileGetStartedFragmentToOtpVerifyFragment(
                                    name = mUserFullName,
                                    email = mUserEmail,
                                    countryCode = mCountryCode,
                                    mobileNo = mUserMobile,
                                    password = mUserPassword,
                                    isEmail = isEmail,
                                    isMobile = isMobile,
                                )
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }
}