package com.wss.eat_space_iz.ui.fragment.profileTab

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wss.eat_space_iz.databinding.FragmentChangePasswordBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.profileTab.ChangePasswordViewModel
import com.wss.eat_space_iz.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment :
    BaseFrag<FragmentChangePasswordBinding, ChangePasswordViewModel>(Layouts.fragment_change_password) {

    private var mUserCurrentPassword: String? = null
    private var mUserNewPassword: String? = null
    private var mUserConfirmPassword: String? = null
    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentChangePasswordBinding.inflate(layoutInflater)
    override val vm: ChangePasswordViewModel by viewModels()

    override fun init() {
        setUpToolbar()
        setUpListener()
    }

    private fun setUpListener() {
        with(binding) {
            btnSave.setOnClickListener(this@ChangePasswordFragment)
            mtbChangePassword.imgBtnBack.setOnClickListener(this@ChangePasswordFragment)
        }
    }

    private fun setUpToolbar() {
        with(binding.mtbChangePassword) {
            tvMyProfile.text = getText(Strings.profile_change_password)
            imgBtnBack.setImageResource(Drawables.left_back_arrow_icon)
        }
    }

   /* private fun userChangePassword(changePasswordRequest: ChangePasswordRequest) {
        vm.changePassword(changePasswordRequest)
    }*/

    private fun validateUserPasswordChangeReq() {
        mUserCurrentPassword = binding.tietExistingPassword.text.toString().trim()
        mUserNewPassword = binding.tietNewPassword.text.toString().trim()
        mUserConfirmPassword = binding.tietConfirmPassword.text.toString().trim()
        when {
            ValidationUtil.isValidateField(requireContext(),
                binding.root,
                mUserCurrentPassword,
                getString(Strings.valid_empty_existing_password)) -> {
                when {
                    ValidationUtil.isValidateField(requireContext(),
                        binding.root,
                        mUserNewPassword,
                        getString(Strings.valid_empty_new_password)) -> {
                        when {
                            ValidationUtil.isValidatePassword(mUserNewPassword) -> {
                                when {
                                    ValidationUtil.isValidateFieldEqual(requireContext(),
                                        binding.root,
                                        mUserNewPassword!!,
                                        mUserConfirmPassword!!,
                                        getString(Strings.valid_password_confirm_password)) -> {
                                      /*  val changePasswordRequest = ChangePasswordRequest(
                                            userId = 71,
                                            currentPassword = mUserCurrentPassword!!,
                                            password = mUserNewPassword!!
                                        )
                                        userChangePassword(changePasswordRequest)*/
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

        }

    }

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {
                when (apiRenderState.result) {
                    /*is ChangePasswordResponse -> {
                        val model = apiRenderState.result
                        model.let {
                            successToast(model.message)
                            findNavController().popBackStack()
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
                btnSave -> {
                    validateUserPasswordChangeReq()
                }
                mtbChangePassword.imgBtnBack -> {
                    findNavController().popBackStack()
                }
                else -> {}
            }
        }
    }
}