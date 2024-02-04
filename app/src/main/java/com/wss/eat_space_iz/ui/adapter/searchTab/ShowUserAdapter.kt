package com.wss.eat_space_iz.ui.adapter.searchTab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wss.eat_space_iz.data.networkModels.searchUser.Item
import com.wss.eat_space_iz.databinding.CustomPopularNearYouListItemLayoutBinding
import com.wss.eat_space_iz.databinding.CustomSearchForReviewItemLayoutBinding
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts

class ShowUserAdapter(private val data : List<Item>):
    RecyclerView.Adapter<ShowUserAdapter.MyViewHolder>()
{

    companion object {
        private var onItemClickListener: OnItemClickListener? = null
    }


    private lateinit var binding: CustomSearchForReviewItemLayoutBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowUserAdapter.MyViewHolder {
        context = parent.context
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            Layouts.custom_search_for_review_item_layout,
            parent,
            false
        )
        return ShowUserAdapter.MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ShowUserAdapter.MyViewHolder, position: Int) {

        val data = data[position]
        with(binding) {



                ivSearchSuggestion.load(data.avatarUrl) {
                    placeholder(Drawables.burger_icon)
                    error(Drawables.burger_icon)
                }
                tvSearchSuggestion.text = data.login



            //tvFollowersCount.text = data.id.toString()
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


    class MyViewHolder(val binding: CustomSearchForReviewItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
            init {
                    binding.tvSearchSuggestion.setOnClickListener{ v ->
                    onItemClickListener!!.onItemClick(absoluteAdapterPosition, v)
                    }
            }

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        ShowUserAdapter.onItemClickListener = onItemClickListener
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int, v: View)
    }


}