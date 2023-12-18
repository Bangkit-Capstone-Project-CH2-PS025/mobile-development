package com.itinergo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itinergo.data.response.savedplace.DataSavedPlace
import com.itinergo.databinding.SavedPlaceItemBinding

class SavedPlaceAdapter(private var itemClick: ListPlaceInterface) :
    RecyclerView.Adapter<SavedPlaceAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DataSavedPlace>() {
        override fun areItemsTheSame(
            oldItem: DataSavedPlace,
            newItem: DataSavedPlace
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DataSavedPlace,
            newItem: DataSavedPlace
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(private val binding: SavedPlaceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: DataSavedPlace) {

            binding.tvTripNameSaved.text = "${item.city.uppercase()}, TRIP"
            binding.tvBudgetSaved.text = "Rp${item.budget}"
            binding.tvDurationNameSaved.text = "${item.duration} day"

            binding.btnDetailsSavedPlace.setOnClickListener {
                itemClick.place(item.id.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SavedPlaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedPlaceAdapter.ViewHolder, position: Int) {
        val result = differ.currentList[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<DataSavedPlace>) {
        differ.submitList(data)
    }

    interface ListPlaceInterface {
        fun place(id: String)
    }
}