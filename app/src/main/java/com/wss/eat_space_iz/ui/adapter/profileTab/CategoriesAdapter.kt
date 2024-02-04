package com.wss.eat_space_iz.ui.adapter.profileTab

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wss.eat_space_iz.R
import com.wss.eat_space_iz.data.networkModels.tummoc.Item
import com.wss.eat_space_iz.databinding.CustomPopularNearYouListItemLayoutBinding
import com.wss.eat_space_iz.utils.Drawables
import com.wss.eat_space_iz.utils.Layouts
import java.util.*

class CategoriesAdapter(private val data: List<Item>) :
    RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {

    companion object {
        private var onItemClickListener: CategoriesAdapter.OnItemClickListener? = null
    }

    private var isVisible = true
    private lateinit var binding: CustomPopularNearYouListItemLayoutBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            Layouts.custom_popular_near_you_list_item_layout,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val viewCategoriesData = data[position]

        with(binding) {

            foodImage.load(viewCategoriesData.icon) {
                placeholder(Drawables.burger_icon)
                error(Drawables.burger_icon)
            }

            foodName.text = viewCategoriesData.name
            foodPrice.text = viewCategoriesData.price.toString()

            favoriteImage.setOnClickListener{
                val context = favoriteImage.context
                Toast.makeText(context, "Favorite clicked for item at position $position", Toast.LENGTH_SHORT).show()
                //favoriteImage.setImageResource(R.drawable.favorite_selected_icon)

                if (isVisible) {
                    favoriteImage.setImageResource(R.drawable.favorite_un_selected_icon)
                } else {
                    favoriteImage.setImageResource(R.drawable.favorite_selected_icon)
                }
                isVisible = !isVisible

            }

            addButton.setOnClickListener{
                val context = addButton.context
                Toast.makeText(context, "Add an item at position $position", Toast.LENGTH_SHORT).show()

            }
        }


    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        CategoriesAdapter.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClickSelected(position: Int, v: View)
    }

    class MyViewHolder(val binding: CustomPopularNearYouListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

          init {
             // binding.mcvCampaignHistory.setOnClickListener { v ->
              //    onItemClickListener!!.onItemClick(absoluteAdapterPosition, v)
              }
    }
}