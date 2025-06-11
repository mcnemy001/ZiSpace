package com.example.zispace.data.repository

import androidx.lifecycle.LiveData
import com.example.zispace.data.dao.GalleryDao
import com.example.zispace.data.entity.GalleryEntity

class GalleryRepository(private val galleryDao: GalleryDao) {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    val allImages: LiveData<List<GalleryEntity>> = galleryDao.getAllImages()
}
