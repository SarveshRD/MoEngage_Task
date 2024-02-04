package com.wss.eat_space_iz.ui.adapter.discountCode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wss.eat_space_iz.databinding.CustomDiscountCodeListItemLayoutBinding
import com.wss.eat_space_iz.utils.Layouts

class DiscountCodeAdapter(private val data : List<Any>) :
RecyclerView.Adapter<DiscountCodeAdapter.MyViewHolder>(){


    private lateinit var binding: CustomDiscountCodeListItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            Layouts.custom_discount_code_list_item_layout,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


    }
    override fun getItemCount(): Int {
        return data.size
    }


    class MyViewHolder(val binding: CustomDiscountCodeListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /*init {
            binding.mcvCampaignHistory.setOnClickListener { v ->
                onItemClickListener!!.onItemClick(absoluteAdapterPosition, v)
            }
        }*/

    }

}