package com.wss.eat_space_iz.ui.adapter.blogTab.followerAndFollowing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wss.eat_space_iz.data.networkModels.speer.FollowingResponseItem
import com.wss.eat_space_iz.databinding.CustomFollowingListItemLayoutBinding
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts


class FollowingAdapter(private val data : List<FollowingResponseItem>):
    RecyclerView.Adapter<FollowingAdapter.MyViewHolder>()
{

    private lateinit var binding: CustomFollowingListItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingAdapter.MyViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            Layouts.custom_following_list_item_layout,
            parent,
            false
        )
        return FollowingAdapter.MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FollowingAdapter.MyViewHolder, position: Int) {

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


    class MyViewHolder(val binding: CustomFollowingListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /*  init {
              binding.mcvCampaignHistory.setOnClickListener { v ->
                  onItemClickListener!!.onItemClick(absoluteAdapterPosition, v)
              }*/
    }

}