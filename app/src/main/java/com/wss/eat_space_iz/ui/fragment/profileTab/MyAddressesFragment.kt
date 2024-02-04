package com.wss.eat_space_iz.ui.fragment.profileTab

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wss.eat_space_iz.databinding.CustomUserAddressDeleteConfirmationDialogBinding
import com.wss.eat_space_iz.databinding.FragmentMyAddressesBinding
import com.wss.eat_space_iz.ui.adapter.profileTab.MyAddressesAdapter
import com.wss.eat_space_iz.ui.common.ApiRenderState
import com.wss.eat_space_iz.ui.fragment.base.BaseFrag
import com.wss.eat_space_iz.ui.viewModel.profileTab.MyAddressesViewModel
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts
import com.wss.eat_space_iz.utils.Strings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAddressesFragment :
    BaseFrag<FragmentMyAddressesBinding, MyAddressesViewModel>(Layouts.fragment_my_addresses) {

    private var mViewUserAddressData: List<Any>? = null
    private var mMyAddressesAdapter: MyAddressesAdapter? = null
    private lateinit var deleteAddressConfirmationDialog: AlertDialog
    override val hasProgress: Boolean = false
    override fun getViewBinding() = FragmentMyAddressesBinding.inflate(layoutInflater)
    override val vm: MyAddressesViewModel by viewModels()

    override fun init() {
        setUpToolbar()
        setUpListener()
        viewUserAddress()
        // setUpUserAddressDetailsRecycler()
    }

    private fun viewUserAddress() {
       // vm.viewUserAddress("4")
    }

    private fun setUpToolbar() {
        with(binding.myAddressesToolbar) {
            tvMyProfile.text = getText(Strings.profile_my_addresses)
            imgBtnBack.setImageResource(Drawables.left_back_arrow_icon)
        }
    }

    private fun setUpListener() {
        with(binding) {
            btnAddNewAddress.setOnClickListener(this@MyAddressesFragment)
            tvAddNewAddress.setOnClickListener(this@MyAddressesFragment)
            myAddressesToolbar.imgBtnBack.setOnClickListener(this@MyAddressesFragment)
        }
    }

    override fun renderState(apiRenderState: ApiRenderState) {
        when (apiRenderState) {
            is ApiRenderState.Loading -> {
                showProgress()
            }
            is ApiRenderState.ApiSuccess<*> -> {
                when (apiRenderState.result) {
                    /*is ViewUserAddressResponse -> {
                        val model = apiRenderState.result
                        model.status?.let {
                            when (it) {
                                getString(Strings.api_success_status) -> {
                                    mViewUserAddressData = model.data
                                    mViewUserAddressData?.let { viewUserAddressData ->
                                        setUpUserAddressDetailsRecycler(viewUserAddressData)
                                    }
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

    private fun setUpUserAddressDetailsRecycler(viewUserAddressData: List<Any>) {
        mMyAddressesAdapter = MyAddressesAdapter(viewUserAddressData)
        binding.rvMyAddresses.adapter = mMyAddressesAdapter
        mMyAddressesAdapter!!.setOnItemClickListener(object :
            MyAddressesAdapter.OnItemClickListener {
            override fun onItemEditClick(position: Int, v: View) {

            }

            override fun onItemDeleteClick(position: Int, v: View) {
                deleteAddressConfirmDialog(position)
            }
        })
    }

    private fun setOnPrimaryBank(position: Int) {
        mViewUserAddressData?.let { viewUserAddressData ->
            /*val changePartnerPrimaryBankRequest =
                ChangePartnerPrimaryBankRequest(bankId = getBankDetailsData[position].id)
            vm.changePartnerPrimaryBank(changePartnerPrimaryBankRequest)*/
        }
    }


    private fun deleteAddressConfirmDialog(position: Int) {
        val deleteItemBuilder = MaterialAlertDialogBuilder(requireContext())
        val customUserAddressDeleteConfirmationDialogBinding: CustomUserAddressDeleteConfirmationDialogBinding =
            CustomUserAddressDeleteConfirmationDialogBinding.inflate(layoutInflater)
        deleteItemBuilder.setView(customUserAddressDeleteConfirmationDialogBinding.root)
        deleteItemBuilder.setCancelable(false)
        deleteAddressConfirmationDialog = deleteItemBuilder.show()

        with(customUserAddressDeleteConfirmationDialogBinding) {
            tvYes.setOnClickListener {
                deleteUserAddressDetails(position)
            }
            tvNo.setOnClickListener {
                deleteAddressConfirmationDialog.dismiss()
            }
        }
    }

    private fun deleteUserAddressDetails(position: Int) {
        mViewUserAddressData?.let { viewUserAddressData ->
            /*val deletePartnerBankDetailsRequest = DeletePartnerBankDetailsRequest(
                bankId = getBankDetailsData[position].id
            )
            vm.deletePartnerBankDetails(deletePartnerBankDetailsRequest)*/
        }
    }


    override fun onClick(v: View) {
        super.onClick(v)
        with(binding) {
            when (v) {
                tvAddNewAddress -> {
                    val action =
                        MyAddressesFragmentDirections.actionMyAddressesFragmentToAddAddressesFragment()
                    findNavController().navigate(action)
                }
                btnAddNewAddress -> {
                    val action =
                        MyAddressesFragmentDirections.actionMyAddressesFragmentToAddAddressesFragment()
                    findNavController().navigate(action)
                }
                myAddressesToolbar.imgBtnBack -> {
                    findNavController().popBackStack()
                }
                else -> {}
            }
        }
    }
}