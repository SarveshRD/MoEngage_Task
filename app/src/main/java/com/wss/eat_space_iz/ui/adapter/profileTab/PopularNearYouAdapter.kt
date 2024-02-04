package com.wss.eat_space_iz.ui.adapter.profileTab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wss.eat_space_iz.data.networkModels.moEngage.Article
import com.wss.eat_space_iz.data.networkModels.tummoc.Item
import com.wss.eat_space_iz.databinding.CustomPopularNearYouListItemLayoutBinding
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts


class PopularNearYouAdapter(private val data: List<Article>) :
    RecyclerView.Adapter<PopularNearYouAdapter.MyViewHolder>() {

    companion object {
        private var onItemClickListener: OnItemClickListener? = null
    }

    private lateinit var binding: CustomPopularNearYouListItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            Layouts.custom_popular_near_you_list_item_layout,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val viewPopularNearYouData = data[position]
        with(binding) {

            foodImage.load(viewPopularNearYouData.urlToImage) {
                placeholder(Drawables.burger_icon)
                error(Drawables.burger_icon)
            }
           /* vr.text = viewPopularNearYouData.text1
            bigText.text = viewPopularNearYouData.text2*/
            //tvRestaurantRating.text = viewPopularNearYouData.rating
            //ivRestaurantDistance
            foodName.text = "Author Name : "+viewPopularNearYouData.author
            foodPrice.text = "Title : "+viewPopularNearYouData.title
            //ivRestaurantTime
            //tvRestaurantTime.text = viewPopularNearYouData


        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class MyViewHolder(val binding: CustomPopularNearYouListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
           /* binding.mcvRestaurantImg.setOnClickListener { v ->
                onItemClickListener!!.onItemClick(absoluteAdapterPosition, v)
            }*/
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        PopularNearYouAdapter.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, v: View)
    }
}