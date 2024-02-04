package com.wss.eat_space_iz.ui.fragment.loginSignUp

import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.wss.eat_space_iz.databinding.FragmentSignInBinding
import com.wss.eat_space_iz.ui.activity.main.MainActivity
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.loginSignUpTab.SignInViewModel
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.utils.Strings
import com.wss.eat_space_iz.utils.ValidationUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFrag<FragmentSignInBinding, SignInViewModel>(Layouts.fragment_sign_in) {

    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentSignInBinding.inflate(layoutInflater)
    override val vm: SignInViewModel by viewModels()
    private var mPlus: String = "+"
    private var mCountryCode: String = ""
    private var mUserMobile: String = ""
    private var mUserEmail: String = ""
    private var mUserPassword: String = ""

    override fun init() {
        setUpListener()
        if (mCountryCode.isNullOrEmpty())
            mCountryCode = mPlus + binding.codePicker.defaultCountryCode

    }

    private fun setUpListener() {
        with(binding) {
            btnSignIn.setOnClickListener(this@SignInFragment)
            tvForgotPassword.setOnClickListener(this@SignInFragment)
            tvSignUp.setOnClickListener(this@SignInFragment)
            btnUseMobile.setOnClickListener(this@SignInFragment)
            btnUseEmail.setOnClickListener(this@SignInFragment)
        }
    }

    private fun userEmailLoginReq() {
        mUserEmail = binding.tietEmail.text.toString().trim()
        mUserPassword = binding.tietPassword.text.toString().trim()
        when {
            ValidationUtil.isValidEmail(requireContext(), binding.root, mUserEmail) -> {
                when {
                    ValidationUtil.isValidateField(requireContext(),
                        binding.root,
                        mUserPassword,
                        getString(Strings.valid_empty_password)) -> {
                       /* val userLoginEmailRequest = UserLoginEmailRequest(
                            email = mUserEmail,
                            password = mUserPassword
                        )
                        vm.userEmailLogin(userLoginEmailRequest = userLoginEmailRequest)*/
                    }
                }
            }
        }
    }

    private fun userMobileLoginReq() {
        mCountryCode = mPlus + binding.codePicker.selectedCountryCode
        mUserMobile = binding.tietMobile.text.toString().trim()
        mUserPassword = binding.tietPassword.text.toString().trim()
        when {
            ValidationUtil.isValidMobile(requireContext(), binding.root, mUserMobile) -> {
                when {
                    ValidationUtil.isValidateField(requireContext(),
                        binding.root,
                        mUserPassword,
                        getString(Strings.valid_empty_password)) -> {
                        val mobileNumber = mCountryCode + mUserMobile
                       /* val userLoginMobileNoRequest = UserLoginMobileNoRequest(
                            mobileNo = mobileNumber,
                            password = mUserPassword
                        )
                        vm.userMobileNoLogin(userLoginMobileNoRequest = userLoginMobileNoRequest)*/
                    }
                }
            }
        }

    }

    private fun setEmailOrMobileVisibility() {
        with(binding) {
            if (groupEmail.visibility == View.VISIBLE) {
                groupEmail.visibility = View.INVISIBLE
                groupMobile.visibility = View.VISIBLE
                mUserEmail = ""
                tietEmail.text?.clear()
            } else {
                groupMobile.visibility = View.INVISIBLE
                groupEmail.visibility = View.VISIBLE
                mUserMobile = ""
                tietMobile.text?.clear()
            }
        }
    }


    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {
                when (apiRenderState.result) {
                    /*is UserLoginResponse -> {
                        val model = apiRenderState.result
                        model.status.let {
                            when (it) {
                                getString(Strings.api_success_status) -> {
                                    with(prefs) {
                                        val gson = Gson()
                                        val loginData = model.data
                                        isUserLogin = true
                                        userToken = gson.toJson(model.token)
                                        userId = gson.toJson(loginData.id.toString())
                                        userProfilePic = gson.toJson(loginData.profilePic)
                                        userProfileDetails = gson.toJson(loginData)
                                        moveToMainActivity()
                                    }
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

    private fun moveToMainActivity() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }

    override fun onClick(v: View) {
        super.onClick(v)
        with(binding) {
            when (v) {
                btnUseEmail -> setEmailOrMobileVisibility()
                btnUseMobile -> setEmailOrMobileVisibility()
                btnSignIn -> {
                    if (groupEmail.visibility == View.VISIBLE)
                        userEmailLoginReq()
                    else
                        userMobileLoginReq()
                }
                tvForgotPassword -> {
                    val action =
                        SignInFragmentDirections.actionSignInFragmentToPasswordRecoveryFragment()
                    findNavController().navigate(action)
                }
                tvSignUp -> {
                    val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
                    findNavController().navigate(action)
                }
            }
        }
    }


}