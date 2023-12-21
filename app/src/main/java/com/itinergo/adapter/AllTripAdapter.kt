package com.itinergo.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itinergo.R
import com.itinergo.data.response.findtrip.Data
import com.itinergo.databinding.FindTripListItemBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AllTripAdapter(private var itemClick: ListPlaceInterface) :
    RecyclerView.Adapter<AllTripAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(private val binding: FindTripListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n")
        fun bind(item: Data?) {
            val inputDeparture = item?.departure
            val inputUntil = item?.until
            val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
            val outputFormat = DateTimeFormatter.ofPattern("dd MMMM")

            val instantDeparture = Instant.from(inputFormat.parse(inputDeparture))
            val date = LocalDateTime.ofInstant(instantDeparture, ZoneId.systemDefault())

            val instantUntil = Instant.from(inputFormat.parse(inputUntil))
            val dateUntil = LocalDateTime.ofInstant(instantUntil, ZoneId.systemDefault())
            val departure: String = outputFormat.format(date)
            val until: String = outputFormat.format(dateUntil)


            binding.tvNameFtrip.text =  item?.user?.name
            binding.tvDateFtrip.text = "$departure - $until"
            binding.tvPersonsFtrip.text = "${item?.persons} persons"
            binding.tvPlaceFtrip.text = "${item?.city}, ${item?.country}"
            Glide.with(itemView.context)
                .load(item?.image)
                .into(binding.ivViewFtrip)

            if(item?.user?.images != null) {
                Glide.with(itemView.context)
                    .load(item.user.images)
                    .into(binding.ivAvatarFtrip)
            } else {
                Glide.with(itemView.context)
                    .asDrawable()
                    .load(ContextCompat.getDrawable(itemView.context, R.drawable.baseline_account_circle_24))
                    .into(binding.ivAvatarFtrip)
            }
            binding.btnTrip.setOnClickListener {
                itemClick.trip(item?.contact.toString())
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FindTripListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: AllTripAdapter.ViewHolder, position: Int) {
        val result = differ.currentList[position]
        if (result != null) {
            holder.bind(result)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<Data>) {
        differ.submitList(data)
    }

    interface ListPlaceInterface {
        fun trip(contact: String)
    }
}