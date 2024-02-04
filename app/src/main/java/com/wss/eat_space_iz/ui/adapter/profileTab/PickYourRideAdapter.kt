package com.wss.eat_space_iz.ui.adapter.profileTab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wss.eat_space_iz.databinding.CustomPickYourRideItemLayoutBinding

class PickYourRideAdapter(
    private val pickYourRideDataList: List<Any?>,
    private val mTotalDistance: Float?
) :
    RecyclerView.Adapter<PickYourRideAdapter.MyViewHolder>() {

    private lateinit var context: Context

    companion object {
        private var onItemClickListener: OnItemClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val binding = CustomPickYourRideItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = pickYourRideDataList[position]
        with(holder.itemBinding) {
           /* tvCabType.text = data?.name
            tvCabPrice.text = (data?.costPerKm?.times(mTotalDistance!!)?.toInt()).toString()
            tvCabSize.text = data?.numOfSeats
            ivCab.load(data?.img) {
                placeholder(Drawables.ic_placeholder)
                error(Drawables.ic_placeholder)
            }

            if (data != null) {
                if (data.isSelected) {
                    holder.itemBinding.root.setBackgroundColor(Color.parseColor("#1A6766FF"))
                } else {
                    holder.itemBinding.root.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
            }*/

        }
    }

    override fun getItemCount(): Int = pickYourRideDataList.size

    class MyViewHolder(itemBinding: CustomPickYourRideItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val itemBinding = itemBinding

        init {
            itemBinding.clPickYourRide.setOnClickListener { v ->
                onItemClickListener!!.onItemClick(absoluteAdapterPosition, v)
            }
        }

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        PickYourRideAdapter.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, v: View)
    }

}


