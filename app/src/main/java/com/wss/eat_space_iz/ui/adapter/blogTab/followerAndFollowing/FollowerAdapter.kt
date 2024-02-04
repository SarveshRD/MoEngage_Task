package com.wss.eat_space_iz.ui.adapter.blogTab.followerAndFollowing

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wss.eat_space_iz.data.networkModels.speer.FollowersResponceItem
import com.wss.eat_space_iz.databinding.CustomFollowersListItemLayoutBinding
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts

class FollowerAdapter(private val data : List<FollowersResponceItem>):
    RecyclerView.Adapter<FollowerAdapter.MyViewHolder>()
{


    private lateinit var binding: CustomFollowersListItemLayoutBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerAdapter.MyViewHolder {
        context = parent.context
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            Layouts.custom_followers_list_item_layout,
            parent,
            false
        )
        return FollowerAdapter.MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FollowerAdapter.MyViewHolder, position: Int) {

        val data = data[position]
        with(binding) {

            civFollowerProfile.load(data.avatarUrl) {
                placeholder(Drawables.burger_icon)
                error(Drawables.burger_icon)
            }
            tvFollowerName.text = data.login
            tvFollowersCount.text = data.id.toString()


        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


    class MyViewHolder(val binding: CustomFollowersListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /*  init {
              binding.mcvCampaignHistory.setOnClickListener { v ->
                  onItemClickListener!!.onItemClick(absoluteAdapterPosition, v)
              }*/
    }


}