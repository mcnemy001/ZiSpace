package com.example.zispace.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zispace.data.entity.GalleryEntity
import com.example.zispace.databinding.ItemGalleryBinding

class GalleryAdapter : ListAdapter<GalleryEntity, GalleryAdapter.GalleryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GalleryViewHolder(private val binding: ItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(galleryImage: GalleryEntity) {
            val context = binding.root.context
            val resId = context.resources.getIdentifier(galleryImage.imageName, "drawable", context.packageName)
            if (resId != 0) {
                binding.ivGalleryImage.setImageResource(resId)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<GalleryEntity>() {
            override fun areItemsTheSame(oldItem: GalleryEntity, newItem: GalleryEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: GalleryEntity, newItem: GalleryEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}