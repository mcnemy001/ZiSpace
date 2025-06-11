package com.example.zispace.ui.musicvideo

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zispace.data.entity.MusicEntity
import com.example.zispace.databinding.ItemMusicBinding

class MusicAdapter : ListAdapter<MusicEntity, MusicAdapter.MusicViewHolder>(DiffCallback) {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    class MusicViewHolder(private val binding: ItemMusicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(music: MusicEntity) {
            binding.tvMusicTitle.text = music.title
            binding.tvMusicArtist.text = music.artist
            val context = binding.root.context
            val resId = context.resources.getIdentifier(music.albumArt, "drawable", context.packageName)
            if (resId != 0) binding.ivAlbumArt.setImageResource(resId)

            // Tambahkan OnClickListener
            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(music.url))
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<MusicEntity>() {
            override fun areItemsTheSame(oldItem: MusicEntity, newItem: MusicEntity) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: MusicEntity, newItem: MusicEntity) = oldItem == newItem
        }
    }
}