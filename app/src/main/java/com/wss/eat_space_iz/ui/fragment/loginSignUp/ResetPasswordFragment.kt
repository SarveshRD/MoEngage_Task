package com.wss.eat_space_iz.ui.fragment.loginSignUp

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.wss.eat_space_iz.databinding.FragmentResetPasswordBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.loginSignUpTab.ResetPasswordViewModel
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.utils.Strings
import com.wss.eat_space_iz.utils.ToastUtil
import com.wss.eat_space_iz.utils.ValidationUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment :
    BaseFrag<FragmentResetPasswordBinding, ResetPasswordViewModel>(Layouts.fragment_reset_password) {

    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentResetPasswordBinding.inflate(layoutInflater)
    override val vm: ResetPasswordViewModel by viewModels()
    private val args: ResetPasswordFragmentArgs by navArgs()
    private lateinit var mUserEmail: String
    private var mUserPassword: String? = null
    private var mUserConfirmPassword: String? = null

    override fun init() {
        setUpListener()
        mUserEmail = args.emailId
    }

    private fun setUpListener() {
        with(binding) {
            btnCancel.setOnClickListener(this@ResetPasswordFragment)
            btnSendEmail.setOnClickListener(this@ResetPasswordFragment)
        }
    }

    private fun validateUserPasswordReq() {
        mUserPassword = binding.tietPassword.text.toString().trim()
        mUserConfirmPassword = binding.tietConfirmPassword.text.toString().trim()
        when {
            ValidationUtil.isValidateField(requireContext(),
                binding.root,
                mUserPassword,
                getString(Strings.valid_empty_password)) -> {
                when {
                    ValidationUtil.isValidatePassword(mUserPassword) -> {
                        when {
                            ValidationUtil.isValidateFieldEqual(requireContext(),
                                binding.root,
                                mUserPassword!!,
                                mUserConfirmPassword!!,
                                getString(Strings.valid_password_confirm_password)) -> {
                                /*val userResetPasswordRequest =
                                    UserResetPasswordRequest(email = mUserEmail,
                                        password = mUserPassword!!)
                                userPasswordReset(userResetPasswordRequest)*/
                            }
                            else -> errorToast(getString(Strings.valid_password_confirm_password))
                        }
                    }
                    else -> {
                        ToastUtil.showErrorToast(requireContext(),
                            getString(Strings.password_instructions))
                    }
                }
            }
        }

    }

   /* private fun userPasswordReset(userResetPasswordRequest: UserResetPasswordRequest) {
        vm.userPasswordReset(userResetPasswordRequest)
    }*/

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {
                when (apiRenderState.result) {
                   /* is UserResetPasswordResponse -> {
                        val model = apiRenderState.result
                        model.let {
                            successToast(model.message)
                            findNavController().popBackStack(R.id.signInFragment, true)
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
        with(binding) {
            when (v) {
                btnCancel -> {
                    findNavController().popBackStack()
                }
                btnSendEmail -> {
                    validateUserPasswordReq()
                }
                else -> {}
            }
        }
    }
}