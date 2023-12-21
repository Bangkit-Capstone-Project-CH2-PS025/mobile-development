package com.itinergo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.itinergo.data.response.budgeting.DataBudgeting
import com.itinergo.data.response.savedplace.DataSavedPlace
import com.itinergo.databinding.SavedPlaceItemBinding

class TravelBudgetingAdapter(private var itemClick: ListPlaceInterface) :
    RecyclerView.Adapter<TravelBudgetingAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DataBudgeting>() {
        override fun areItemsTheSame(
            oldItem: DataBudgeting,
            newItem: DataBudgeting
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DataBudgeting,
            newItem: DataBudgeting
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(private val binding: SavedPlaceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: DataBudgeting) {

            binding.tvTripNameSaved.text = item.budgetName
            binding.tvBudgetSaved.visibility = View.GONE
            binding.tvDurationNameSaved.visibility = View.GONE

            binding.btnDetailsSavedPlace.setOnClickListener {
                itemClick.budget(item.id.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SavedPlaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TravelBudgetingAdapter.ViewHolder, position: Int) {
        val result = differ.currentList[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<DataBudgeting>) {
        differ.submitList(data)
    }

    interface ListPlaceInterface {
        fun budget(id: String)
    }
}