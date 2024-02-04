package com.wss.eat_space_iz.ui.adapter.profileTab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wss.eat_space_iz.databinding.CustomMyAddressesListItemLayoutBinding
import com.wss.eat_space_iz.utils.Layouts

class MyAddressesAdapter(private val mViewUserAddressData: List<Any>) :
    RecyclerView.Adapter<MyAddressesAdapter.MyViewHolder>() {

    companion object {
        private var onItemClickListener: OnItemClickListener? = null
    }

    private lateinit var binding: CustomMyAddressesListItemLayoutBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            Layouts.custom_my_addresses_list_item_layout,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val viewUserAddressData = mViewUserAddressData[position]
        with(binding) {
           // tvAddressMain.text = viewUserAddressData.buildingName
            //tvAddressFull.text = viewUserAddressData.extra + "\n" + viewUserAddressData.landmark
        }
    }

    override fun getItemCount(): Int {
        return mViewUserAddressData.size
    }

    class MyViewHolder(val binding: CustomMyAddressesListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                tvAddressDelete.setOnClickListener { v ->
                    onItemClickListener!!.onItemDeleteClick(absoluteAdapterPosition, v)
                }
                ivAddressDelete.setOnClickListener { v ->
                    onItemClickListener!!.onItemDeleteClick(absoluteAdapterPosition, v)
                }
                tvAddressEdit.setOnClickListener { v ->
                    onItemClickListener!!.onItemEditClick(absoluteAdapterPosition, v)
                }
                ivAddressEdit.setOnClickListener { v ->
                    onItemClickListener!!.onItemEditClick(absoluteAdapterPosition, v)
                }
            }
        }

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        MyAddressesAdapter.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemEditClick(position: Int, v: View)
        fun onItemDeleteClick(position: Int, v: View)
    }
}