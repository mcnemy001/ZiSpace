package com.example.zispace.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.zispace.data.entity.GalleryEntity
import com.example.zispace.data.repository.GalleryRepository

class GalleryViewModel(repository: GalleryRepository) : ViewModel() {
    val galleryImages: LiveData<List<GalleryEntity>> = repository.allImages
}