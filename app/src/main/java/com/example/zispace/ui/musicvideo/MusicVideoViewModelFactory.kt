package com.example.zispace.ui.musicvideo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zispace.data.repository.MusicVideoRepository

class MusicVideoViewModelFactory(private val repository: MusicVideoRepository) : ViewModelProvider.Factory {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicVideoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MusicVideoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
