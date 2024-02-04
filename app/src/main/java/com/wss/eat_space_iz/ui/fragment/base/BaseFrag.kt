package com.wss.eat_space_iz.ui.fragment.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.github.drjacky.imagepicker.ImagePicker
import com.smokelaboratory.freedom.Freedom
import com.wss.eat_space_iz.R
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz_restaurant.ui.intfc.ImagePickerListener
import com.wss.eat_space_iz.ui.viewModel.base.BaseVM
import com.wss.eat_space_iz.utils.ToastUtil
import com.wss.eat_space_iz.utils.getFileFromContentUri
import com.wss.eat_space_iz.utils.sessionManagers.UserSessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


abstract class BaseFrag<binding : ViewBinding, VM : BaseVM>(
    @LayoutRes private val layoutId: Int,
) : Fragment(), View.OnClickListener, ImagePickerListener {

    @Inject
    lateinit var prefs: UserSessionManager

    /*@Inject
    lateinit var dataStore: DataStoreUtil*/

    protected lateinit var binding: binding

    //protected lateinit var rootActivity: BaseAct<*, *>
    //protected var locationFetchUtil: LocationCallUtil? = null
    protected var freedom: Freedom? = null

    private var progress: Boolean? = false

    protected abstract fun renderState(apiRenderState: ApiRenderState)
    protected abstract val hasProgress: Boolean
    protected abstract val vm: VM?
    protected abstract fun init()
    abstract fun getViewBinding(): binding

    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * If a parent Fragment contains nested Fragments or multiple levels of nested Fragments,
         * then by default, they all use the same FragmentFactory from the parent Fragment unless overridden.
         */

        /*fragFactory?.let {
            childFragmentManager.fragmentFactory = it
        }*/
        super.onCreate(savedInstanceState)
    }

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
                        if (it.resCode == UNAUTHORIZED_CODE)
                            (requireActivity() as BaseAct<ViewBinding, BaseVM>).logout(true)
                        else
                            if (it.resultType != CANCELLED) {
                                hideProgress()
                                it.error?.let { k ->
                                    if (it.resCode != BANK_DETAIL_ERROR)
                                        errorToast(k)
                                }
                            }
                    }
                }*/
            }
//            setVariable(BR.click, this@BaseFrag)
        }

        if (hasProgress) {
//            progress = ObservableField()
//            binding.setVariable(BR.showProgress, progress)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    /*fun startActivity(
        act: Class<*>,
        bundle: Bundle? = null,
        flags: List<Int>? = null,
        shouldAnimate: Boolean = true
    ) {
        val intent = Intent(requireActivity(), act)

        if (bundle != null)
            intent.putExtras(bundle)

        if (!flags.isNullOrEmpty())
            flags.forEach {
                intent.addFlags(it)
            }

        if (shouldAnimate)
            startActivity(
                intent,
                ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out)
                    .toBundle()
            )
        else
            startActivity(intent)
    }

    fun startActivityForResult(
        act: Class<*>,
        requestCode: Int = 0,
        bundle: Bundle? = null,
        flags: List<Int>? = null,
        shouldAnimate: Boolean = true
    ) {
        val intent = Intent(requireActivity(), act)

        if (bundle != null)
            intent.putExtras(bundle)

        if (!flags.isNullOrEmpty())
            flags.forEach {
                intent.addFlags(it)
            }

        if (shouldAnimate)
            startActivityForResult(
                intent,
                requestCode,
                ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out)
                    .toBundle()
            )
        else
            startActivityForResult(intent, requestCode)
    }

    inline fun <reified T : Fragment> addFrag(
        container: Int,
        addToBackStack: Boolean = false,
        shouldAnimate: Boolean = true,
        bundle: Bundle? = null
    ) {
        childFragmentManager.commit {
            if (shouldAnimate)
                setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            if (addToBackStack)
                addToBackStack(T::class.java.name)
            add<T>(container, args = bundle)
        }
    }

    fun addFrag(
        fragment: Fragment,
        container: Int,
        addToBackStack: Boolean = false,
        shouldAnimate: Boolean = true,
        bundle: Bundle? = null
    ) {
        childFragmentManager.commit {
            if (shouldAnimate)
                setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            if (addToBackStack)
                addToBackStack(fragment::class.java.name)
            if (bundle != null)
                fragment.arguments = bundle

            add(container, fragment)
        }
    }

    inline fun <reified T : Fragment> replaceFrag(
        container: Int,
        addToBackStack: Boolean = false,
        shouldAnimate: Boolean = true,
        bundle: Bundle? = null
    ) {
        childFragmentManager.commit {
            if (shouldAnimate)
                setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            if (addToBackStack)
                addToBackStack(T::class.java.name)
            replace<T>(container, args = bundle)
        }
    }

    fun replaceFrag(
        fragment: Fragment,
        container: Int,
        addToBackStack: Boolean = false,
        shouldAnimate: Boolean = true,
        bundle: Bundle? = null
    ) {
        childFragmentManager.commit {
            if (shouldAnimate)
                setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            if (addToBackStack)
                addToBackStack(fragment::class.java.name)
            if (bundle != null)
                fragment.arguments = bundle

            replace(container, fragment)
        }
    }*/

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

    fun delayedExecutor(millis: Long, executable: () -> Unit) {
        lifecycleScope.launch {
            delay(millis)
            executable.invoke()
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        locationFetchUtil?.onActivityResult(requestCode, resultCode, data)
        freedom?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        locationFetchUtil?.onRequestPermissionsResult(requestCode, permissions, grantResults)
        freedom?.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }*/

    override fun onClick(v: View) {

    }

    private var imagePicker = ImagePicker

    fun <T : Any> Fragment.setBackStackData(key: String, data: T, doBack: Boolean = true) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(key, data)
        if (doBack)
            findNavController().popBackStack()
    }

    fun <T : Any> Fragment.getBackStackData(key: String, result: (T) -> (Unit)) {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
            ?.observe(viewLifecycleOwner) {
                result(it)
            }
    }

    protected fun selectImage() {
        val items = arrayOf<CharSequence>(
            "Take Photo", "Choose from Library",
            "Cancel"
        )
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Add Photo!")
        builder.setItems(items) { dialog, item ->
            when {
                (items[item] == "Take Photo") -> {
                    launcher.launch(imagePicker.with(requireActivity()).cameraOnly().createIntent())
                }
                (items[item] == "Choose from Library") -> {
                    launcher.launch(
                        imagePicker.with(requireActivity()).galleryOnly().createIntent()
                    )

                    /*launcher.launch(
                            imagePicker.with(requireActivity()).galleryOnly().createIntent()
                                .setType("application/pdf").setAction(Intent.ACTION_OPEN_DOCUMENT)
                                .addCategory(Intent.CATEGORY_OPENABLE)
                                .putExtra(Intent.EXTRA_MIME_TYPES,"application/pdf")
                        )*/


                    /*launcher.launch(
                        imagePicker.with(requireActivity())
                            .galleryMimeTypes(arrayOf("application/pdf")).galleryOnly().createIntent()
                            .setAction(Intent.ACTION_OPEN_DOCUMENT)
                            .addCategory(Intent.CATEGORY_OPENABLE)
                    )*/


                }
                (items[item] == "Cancel") -> dialog.dismiss()
            }
        }
        builder.show()

    }

    protected fun closeAppDialog() {
        val items = arrayOf<CharSequence>("Yes", "No")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Close App!")
            .setItems(items) { dialog, item ->
                when {
                    (items[item] == "Yes") -> {
                        requireActivity().finish()
                    }
                    (items[item] == "No") -> dialog.dismiss()
                }
            }

        builder.show()
    }


    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                val profilePictureUri = it?.data!!.data?.getFileFromContentUri(
                    requireContext(),
                    "Profile"
                )?.path.orEmpty()
                val file = File(profilePictureUri)
                if (uri != null && !file.path.isNullOrEmpty())
                    getSelectedFile(file)
                else
                    errorToast(getString(R.string.error_camera_gallery_file_pick))
            }

        }

    override fun getSelectedFile(file: File) {

    }


}
