package com.example.zispace.ui.musicvideo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.zispace.data.entity.MusicEntity
import com.example.zispace.data.entity.VideoEntity
import com.example.zispace.data.repository.MusicVideoRepository

class MusicVideoViewModel(repository: MusicVideoRepository) : ViewModel() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    val favoriteMusic: LiveData<List<MusicEntity>> = repository.allMusic
    val favoriteVideos: LiveData<List<VideoEntity>> = repository.allVideos
}