package com.wss.eat_space_iz.ui.fragment.loginSignUp

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wss.eat_space_iz.databinding.FragmentSignUpBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.loginSignUpTab.SignUpViewModel
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.utils.Strings
import com.wss.eat_space_iz.utils.ToastUtil
import com.wss.eat_space_iz.utils.ValidationUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFrag<FragmentSignUpBinding, SignUpViewModel>(Layouts.fragment_sign_up) {

    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentSignUpBinding.inflate(layoutInflater)
    override val vm: SignUpViewModel by viewModels()
    private lateinit var mUserFullName: String
    private lateinit var mUserEmail: String
    private lateinit var mUserPassword: String

    override fun init() {
        setUpListener()
    }

    private fun setUpListener() {
        with(binding) {
            btnSignUp.setOnClickListener(this@SignUpFragment)
            tvSignIn.setOnClickListener(this@SignUpFragment)
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
                btnSignUp -> {
                    validateSignUpReq()
                }
                tvSignIn -> {
                    findNavController().popBackStack()
                }
                else -> {}
            }
        }
    }

    private fun validateSignUpReq() {
        with(binding) {
            mUserFullName = tietName.text.toString().trim()
            mUserEmail = tietEmail.text.toString().trim()
            mUserPassword = tietPassword.text.toString().trim()
            if (ValidationUtil.isValidFullName(requireContext(), binding.root, mUserFullName)) {
                if (ValidationUtil.isValidEmail(requireContext(), binding.root, mUserEmail)) {
                    if (ValidationUtil.isValidateField(requireContext(), binding.root, mUserPassword, getString(Strings.valid_empty_password))){
                        if (ValidationUtil.isValidatePassword(mUserPassword)) {
                            val action = SignUpFragmentDirections.actionSignUpFragmentToOtpVerifyFragment(
                                name = mUserFullName,
                                email = mUserEmail,
                                password = mUserPassword,
                                mobileNo = null,
                                countryCode = null,
                                isEmail = true,
                                isMobile = false
                            )
                            findNavController().navigate(action)
                        } else {
                            ToastUtil.showErrorToast(requireContext(),getString(Strings.password_instructions))
                        }
                    }
                }
            }
        }
    }
}