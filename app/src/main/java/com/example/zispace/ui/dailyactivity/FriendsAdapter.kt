package com.example.zispace.ui.dailyactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zispace.R
import com.example.zispace.data.entity.FriendEntity
import com.example.zispace.databinding.ItemFriendBinding
import com.example.zispace.model.FriendItem

class FriendsAdapter : ListAdapter<FriendEntity, FriendsAdapter.FriendViewHolder>(DiffCallback) {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FriendViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: FriendEntity) { // Ganti tipe parameter
            binding.tvFriendName.text = friend.name
            val context = binding.root.context
            val resId = context.resources.getIdentifier(friend.avatar, "drawable", context.packageName)
            if (resId != 0) {
                binding.ivFriendAvatar.setImageResource(resId)
            } else {
                binding.ivFriendAvatar.setImageResource(R.drawable.ic_person)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FriendEntity>() { // Ganti tipe
            override fun areItemsTheSame(oldItem: FriendEntity, newItem: FriendEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: FriendEntity, newItem: FriendEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}