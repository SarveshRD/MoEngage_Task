package com.wss.eat_space_iz.ui.adapter.profileTab.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wss.eat_space_iz.databinding.CustomMyFavourtiesListItemLayoutBinding
import com.wss.eat_space_iz.utils.Layouts

class MyFavouriteAdapter( private val data : List<Any> ):
RecyclerView.Adapter<MyFavouriteAdapter.MyViewHolder>()
{

    private lateinit var binding: CustomMyFavourtiesListItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            Layouts.custom_my_favourties_list_item_layout,
            parent,
            false
        )
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {}

    override fun getItemCount(): Int {
        return data.size
    }


    class MyViewHolder(val binding: CustomMyFavourtiesListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

      /*  init {
            binding.mcvCampaignHistory.setOnClickListener { v ->
                onItemClickListener!!.onItemClick(absoluteAdapterPosition, v)
            }*/
        }
}
