package com.wss.eat_space_iz.ui.adapter.profileTab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wss.eat_space_iz.data.networkModels.cart.Cart
import com.wss.eat_space_iz.databinding.CustomRestaurantFoodItemListLayoutBinding
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts

class TimeDisplayAdapter(private val data: List<Cart>) :
    RecyclerView.Adapter<TimeDisplayAdapter.MyViewHolder>() {

    companion object {
        private var onItemClickListener: OnItemClickListener? = null
    }

    private lateinit var binding: CustomRestaurantFoodItemListLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            Layouts.custom_restaurant_food_item_list_layout,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val viewCategoriesData = data[position]
        //   val color = "#${viewCategoriesData.colorCode}".toColorInt()
        with(binding) {
            ivFoodType.load(viewCategoriesData.icon) {
                placeholder(Drawables.burger_icon)
                error(Drawables.burger_icon)
            }
            tvFoodName.text = viewCategoriesData.name
            tvFoodPrice.text = viewCategoriesData.price.toString()
            tvUnit.text = viewCategoriesData.unit

            imgBtnMinus.setOnClickListener{
                var count = 2
                count--
                notifyChange()
                tvCounter.text = count.toString()


            }

            imgBtnPlus.setOnClickListener{
                var count = 1
                count++
                notifyChange()
                tvCounter.text = count.toString()

            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class MyViewHolder(val binding: CustomRestaurantFoodItemListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
           /* binding.mcvRestaurantImg.setOnClickListener { v ->
                PopularNearYouAdapter.onItemClickListener!!.onItemClick(absoluteAdapterPosition, v)
            }*/
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        TimeDisplayAdapter.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClickSelected(position: Int, v: View)
    }


}