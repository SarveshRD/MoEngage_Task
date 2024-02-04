package com.wss.eat_space_iz.ui.fragment.loginSignUp

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wss.eat_space_iz.R
import com.wss.eat_space_iz.databinding.FragmentOtpVerifyBinding
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.loginSignUpTab.OtpVerifyViewModel
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.utils.Strings
import com.wss.eat_space_iz.utils.TimerUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpVerifyFragment : BaseFrag<FragmentOtpVerifyBinding, OtpVerifyViewModel>(Layouts.fragment_otp_verify), TimerUtil.OnTimerListener {

    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentOtpVerifyBinding.inflate(layoutInflater)
    override val vm: OtpVerifyViewModel by viewModels()
    private val args: OtpVerifyFragmentArgs by navArgs()
    private lateinit var countDownTimer: TimerUtil
    private var isEmail: Boolean = false
    private var isMobile: Boolean = false
    private var mCountryCode: String? = null
    private var mUserMobile: String? = null
    private var mUserFullName: String? = null
    private var mUserEmail: String? = null
    private var mUserPassword: String? = null
    private var mEnterOtp: String? = null
    private var mResOTP: String? = null

    override fun init() {
        setUpListener()
        setUpArguments()
        sendOtp()

        binding.pinView.setPinViewEventListener { pinview, fromUser ->
            mEnterOtp = pinview.value
        }
    }

    private fun sendOtp() {
        when {
            isEmail && isMobile -> {
                mCountryCode?.let { countryCode ->
                    mUserMobile?.let { userMobile ->
                        val mobileNo = countryCode + userMobile
                        sendMobileOTP(mobileNo)
                    }
                }
            }
            isEmail -> {
                mUserEmail?.let { userEmail ->
                    sendEmailOTP(userEmail)
                }
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

    private fun sendMobileOTP(mUserMobile: String) {
       /* val sendMobileOtpRequest = SendMobileOtpRequest(mobileNo = mUserMobile)
        vm.sendMobileOtp(sendMobileOtpRequest = sendMobileOtpRequest)*/
        startTimer()
    }

    private fun sendEmailOTP(mUserEmail: String) {
      /*  val sendEmailOtpRequest = SendEmailOtpRequest(emailId = mUserEmail)
        vm.sendEmailOtp(sendEmailOtpRequest = sendEmailOtpRequest)*/
        startTimer()
    }

    private fun startTimer() {
        countDownTimer = TimerUtil(60 * 1000, 1000, this)
        countDownTimer.start()
        binding.tvResend.visibility = View.VISIBLE
        binding.tvDidnTReceiveCode.visibility = View.VISIBLE
        binding.tvResendOtp.visibility = View.GONE
    }

   /* private fun userSignUp(createUserAccountRequest: CreateUserAccountRequest) {
        vm.userSignUp(createUserAccountRequest = createUserAccountRequest)
    }*/

    private fun setUpListener() {
        with(binding) {
            btnContinue.setOnClickListener(this@OtpVerifyFragment)
            tvResend.setOnClickListener(this@OtpVerifyFragment)
        }
    }

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {
                when (apiRenderState.result) {
                   /* is SendEmailOtpResponse -> {
                        val model = apiRenderState.result
                        model.status.let {
                            when (it) {
                                getString(Strings.api_success_status) -> {
                                    mResOTP = model.data.otp.toString()
                                    successToast(mResOTP!!)
                                }
                            }
                        }
                    }
                    is SendMobileOtpResponse -> {
                        val model = apiRenderState.result
                        model.status.let {
                            when (it) {
                                getString(Strings.api_success_status) -> {
                                    mResOTP = model.data.otp.toString()
                                    successToast(mResOTP!!)
                                }
                            }
                        }
                    }
                    is CreateUserAccountResponse -> {
                        val model = apiRenderState.result
                        model.status.let {
                            when (it) {
                                getString(Strings.api_success_status) -> {
                                    successToast(getString(R.string.registration_Successful))
                                    findNavController().popBackStack(R.id.signInFragment, true)
                                }
                                else -> {}
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

    override fun onClick(v: View) {
        super.onClick(v)
        with(binding) {
            when (v) {
                btnContinue -> {
                    when {
                        isEmail && isMobile -> {
                            if (mEnterOtp == mResOTP)
                                mEnterOtp?.let {
                                    mUserFullName?.let { userFullName ->
                                        mUserEmail?.let { userEmail ->
                                            mCountryCode?.let { countryCode ->
                                                mUserMobile?.let { userMobile ->
                                                    mUserPassword?.let { userPassword ->
                                                        /*val createUserAccountRequest =
                                                            CreateUserAccountRequest(
                                                                fullName = userFullName,
                                                                email = userEmail,
                                                                countryCode = countryCode,
                                                                mobileNo = userMobile,
                                                                password = userPassword,
                                                                profileStatus = true,
                                                                profilePic = ""
                                                            )
                                                        userSignUp(createUserAccountRequest)*/
                                                    }
                                                        ?: errorToast(getString(Strings.api_something_went_wrong))
                                                }
                                                    ?: errorToast(getString(Strings.api_something_went_wrong))
                                            }
                                                ?: errorToast(getString(Strings.api_something_went_wrong))
                                        } ?: errorToast(getString(Strings.api_something_went_wrong))
                                    } ?: errorToast(getString(Strings.api_something_went_wrong))
                                }
                            else
                                errorToast(getString(Strings.valid_otp_not_match))
                        }
                        isEmail -> {
                            if (mEnterOtp == mResOTP)
                                mEnterOtp?.let {
                                    val action =
                                        OtpVerifyFragmentDirections.actionOtpVerifyFragmentToMobileGetStartedFragment(
                                            email = mUserEmail,
                                            name = mUserFullName,
                                            password = mUserPassword,
                                            mobileNo = null,
                                            countryCode = null,
                                            isEmail = true,
                                            isMobile = true
                                        )
                                    findNavController().navigate(action)
                                    successToast(mEnterOtp!!)
                                }
                            else
                                errorToast(getString(Strings.valid_otp_not_match))
                        }
                        else -> {}
                    }
                }
                else -> {}
            }
        }
    }

    override fun onGetTimerEvent(time: String) {
        binding.tvResend.text = "Resend(${time.substring(3)}s)"
    }

    override fun onTimerFinish() {
        binding.tvResend.visibility = View.GONE
        binding.tvResendOtp.visibility = View.VISIBLE
        binding.tvDidnTReceiveCode.visibility = View.VISIBLE
    }

}