package com.wss.eat_space_iz.ui.fragment.loginSignUp

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wss.eat_space_iz.R
import com.wss.eat_space_iz.databinding.FragmentPasswordRecoveryBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.loginSignUpTab.PasswordRecoveryViewModel
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.utils.ValidationUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordRecoveryFragment :
    BaseFrag<FragmentPasswordRecoveryBinding, PasswordRecoveryViewModel>(Layouts.fragment_password_recovery) {

    private lateinit var mUserEmail: String
    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentPasswordRecoveryBinding.inflate(layoutInflater)
    override val vm: PasswordRecoveryViewModel by viewModels()

    override fun init() {
        setUpListener()
    }

    private fun setUpListener() {
        with(binding) {
            btnCancel.setOnClickListener(this@PasswordRecoveryFragment)
            btnSendEmail.setOnClickListener(this@PasswordRecoveryFragment)
        }
    }

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {
                when (apiRenderState.result) {
                    /*is UserResetPasswordResponse -> {
                        val model = apiRenderState.result
                        model?.let {
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
                    findNavController().popBackStack(R.id.signInFragment, true)
                }
                btnSendEmail -> {
                    mUserEmail = tietEmail.text.toString().trim()
                    when {
                        ValidationUtil.isValidEmail(requireContext(), binding.root, mUserEmail) -> {
                            val action = PasswordRecoveryFragmentDirections.actionPasswordRecoveryFragmentToResetPasswordFragment(emailId = mUserEmail)
                            findNavController().navigate(action)
                        }
                        else -> {}
                    }
                }
                else -> {}
            }
        }
    }
}