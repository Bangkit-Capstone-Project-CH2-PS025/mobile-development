package com.itinergo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itinergo.data.response.savedplace.DataSavedPlace
import com.itinergo.data.response.traveltips.DataTravelTips
import com.itinergo.databinding.SavedPlaceItemBinding
import com.itinergo.databinding.TravelTipsItemBinding

class TravelTipsAdapter(private var itemClick: ListPlaceInterface) :
    RecyclerView.Adapter<TravelTipsAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DataTravelTips>() {
        override fun areItemsTheSame(
            oldItem: DataTravelTips,
            newItem: DataTravelTips
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DataTravelTips,
            newItem: DataTravelTips
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(private val binding: TravelTipsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: DataTravelTips) {

            binding.tvTitleTravelTipsItem.text = item.title
            binding.tvAuthorTravelTipsItem.text = item.author
            Glide.with(itemView.context).load(item.imageCover).into(binding.ivImgPlace)

            itemView.setOnClickListener {
                itemClick.tips(item.id.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TravelTipsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TravelTipsAdapter.ViewHolder, position: Int) {
        val result = differ.currentList[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<DataTravelTips>) {
        differ.submitList(data)
    }

    interface ListPlaceInterface {
        fun tips(id: String)
    }
}