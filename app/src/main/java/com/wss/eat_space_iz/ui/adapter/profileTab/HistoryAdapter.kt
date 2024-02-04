package com.wss.eat_space_iz.ui.adapter.profileTab

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wss.eat_space_iz.data.networkModels.favitems.Favitem
import com.wss.eat_space_iz.databinding.CustomAllRestaurantsListItemLayoutBinding
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts

class HistoryAdapter(private val data: List<Favitem>) :
    RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    private lateinit var binding: CustomAllRestaurantsListItemLayoutBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            Layouts.custom_all_restaurants_list_item_layout,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val viewCategoriesData = data[position]
        //   val color = "#${viewCategoriesData.colorCode}".toColorInt()
        with(binding) {
            ivRestaurant.load(viewCategoriesData.icon) {
                placeholder(Drawables.burger_icon)
                error(Drawables.burger_icon)
            }
            tvRestaurantName2.text = viewCategoriesData.name
            tvRestaurantAddress2.text = viewCategoriesData.unit.toString()
            tvRestaurantRating2.text = viewCategoriesData.price.toString()

        }


    }

    override fun getItemCount(): Int {
        return data.size
    }


    class MyViewHolder(val binding: CustomAllRestaurantsListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // binding.mcvCampaignHistory.setOnClickListener { v ->
            //    onItemClickListener!!.onItemClick(absoluteAdapterPosition, v)
        }
    }

}