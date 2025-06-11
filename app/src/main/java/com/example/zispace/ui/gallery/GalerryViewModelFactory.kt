package com.example.zispace.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zispace.data.repository.GalleryRepository

class GalleryViewModelFactory(private val repository: GalleryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GalleryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}