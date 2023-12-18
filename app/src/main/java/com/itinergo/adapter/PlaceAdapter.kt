package com.itinergo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itinergo.data.response.visitedplace.DataPlace
import com.itinergo.databinding.PlaceListItemBinding

class PlaceAdapter(private var itemClick: ListPlaceInterface) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DataPlace>() {
        override fun areItemsTheSame(
            oldItem: DataPlace,
            newItem: DataPlace
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DataPlace,
            newItem: DataPlace
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(private val binding: PlaceListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: DataPlace) {

            binding.tvNamePlace.text = "${item.city}, ${item.country}"
            binding.tvTimePlace.text = "${item.month} ${item.year}"
            Glide.with(itemView.context)
                .load(item.image)
                .into(binding.ivPlace)

            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PlaceListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceAdapter.ViewHolder, position: Int) {
        val result = differ.currentList[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<DataPlace>) {
        differ.submitList(data)
    }

    interface ListPlaceInterface {
        fun place(id: String)
    }
}