package com.example.zispace.ui.dailyactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zispace.R
import com.example.zispace.data.entity.DailyActivityEntity
import com.example.zispace.databinding.ItemActivityBinding
import com.example.zispace.model.ActivityItem

class ActivityAdapter : ListAdapter<DailyActivityEntity, ActivityAdapter.ActivityViewHolder>(DiffCallback) {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val binding = ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ActivityViewHolder(private val binding: ItemActivityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: DailyActivityEntity) { // Ganti tipe parameter
            binding.tvActivityName.text = activity.name
            val iconRes = when (activity.icon) {
                "ic_school" -> R.drawable.ic_study
                "ic_work" -> R.drawable.ic_project
                "ic_walk" -> R.drawable.ic_walk
                "ic_design" -> R.drawable.ic_design
                "ic_gaming" -> R.drawable.ic_gaming
                else -> R.drawable.ic_study
            }
            binding.ivActivityIcon.setImageResource(iconRes)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DailyActivityEntity>() {
            override fun areItemsTheSame(oldItem: DailyActivityEntity, newItem: DailyActivityEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: DailyActivityEntity, newItem: DailyActivityEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}