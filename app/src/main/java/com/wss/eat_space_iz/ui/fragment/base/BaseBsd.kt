package com.wss.eat_space_iz.ui.fragment.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wss.eat_space_iz.R
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import com.wss.eat_space_iz.utils.ToastUtil
import com.wss.eat_space_iz.utils.sessionManagers.UserSessionManager
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseBsd<binding : ViewBinding, VM : BaseVM> : BottomSheetDialogFragment() {

    @Inject
    lateinit var prefs: UserSessionManager
    protected lateinit var binding: binding
    protected abstract val layoutId: Int
    protected abstract val vm: VM?
    protected abstract val onClick: View.OnClickListener?
    protected abstract fun init()
    protected abstract fun renderState(apiRenderState: ApiRenderState)
    protected abstract val hasProgress: Boolean
    private var progress: Boolean? = false
    abstract fun getViewBinding(): binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_EATSpaceiz)
    }

    override fun getTheme(): Int = R.style.BsdTheme
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = getViewBinding().apply {
//        binding = DataBindingUtil.inflate<binding>(inflater, layoutId, container, false).apply {
//            lifecycleOwner = this@BaseFrag
            vm?.let {
//                setVariable(BR.vm, it)
                viewLifecycleOwner.lifecycleScope.launch {
                    it.state().collect {
                        renderState(it)
                    }
                }

                /*lifecycleScope.launch {
                    it.apiError.collect {
                        if (it.resCode == AppConstants.Api.ResponseCode.UNAUTHORIZED_CODE)
                            (requireActivity() as BaseAct<ViewBinding, BaseVM>).logout(true)
                        else
                            if (it.resultType != BaseRepo.ApiResultType.CANCELLED) {
                                hideProgress()
                                it.error?.let {
                                    errorToast(it)
                                }
                            }
                    }
                }*/
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*try {
            //fully expand bsd
            dialog!!.setOnShowListener {
                val dialog = it as BottomSheetDialog
                val bottomSheet = dialog.findViewById<View>(R.id.cl_bottomsheet)
                bottomSheet?.let { sheet ->
                    dialog.behavior.peekHeight = sheet.height
                    sheet.parent.parent.requestLayout()
                }
            }
            init()
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
        init()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        init()
    }


    fun showProgress() {
        progress = true
    }

    fun hideProgress() {
        progress = false
    }

    fun errorToast(message: String, callback: ((Boolean) -> Unit)? = null) {
        ToastUtil.errorSnackbar(message, binding.root, callback)
    }

    fun successToast(message: String, callback: ((Boolean) -> Unit)? = null) {
        ToastUtil.successSnackbar(message, binding.root, callback)
    }

//    fun checkForApiUpdate(apiUpdateDate : String?, appUpdateDate : String?) : Boolean{
//        return when {
//            appUpdateDate.isNullOrEmpty() || apiUpdateDate.isNullOrEmpty() -> true
//            else -> {
//                !appUpdateDate!!.getDate(DateTimeUtil.SERVER_DATE_FORMAT)!!
//                    .after(apiUpdateDate!!.getDate(DateTimeUtil.SERVER_DATE_FORMAT))
//            }
//        }
//    }

}
