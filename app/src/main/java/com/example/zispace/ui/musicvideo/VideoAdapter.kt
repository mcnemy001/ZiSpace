package com.example.zispace.ui.musicvideo

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zispace.data.entity.VideoEntity
import com.example.zispace.databinding.ItemVideoBinding

class VideoAdapter : ListAdapter<VideoEntity, VideoAdapter.VideoViewHolder>(DiffCallback) {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025



    class VideoViewHolder(private val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: VideoEntity) {
            binding.tvVideoTitle.text = video.title
            val context = binding.root.context
            val resId = context.resources.getIdentifier(video.thumbnail, "drawable", context.packageName)
            if (resId != 0) binding.ivVideoThumbnail.setImageResource(resId)

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.url))
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<VideoEntity>() {
            override fun areItemsTheSame(oldItem: VideoEntity, newItem: VideoEntity) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: VideoEntity, newItem: VideoEntity) = oldItem == newItem
        }
    }
}