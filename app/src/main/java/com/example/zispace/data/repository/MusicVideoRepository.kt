package com.example.zispace.data.repository

import androidx.lifecycle.LiveData
import com.example.zispace.data.dao.MusicDao
import com.example.zispace.data.dao.VideoDao
import com.example.zispace.data.entity.MusicEntity
import com.example.zispace.data.entity.VideoEntity

class MusicVideoRepository(
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025


    private val musicDao: MusicDao,
    private val videoDao: VideoDao
) {
    val allMusic: LiveData<List<MusicEntity>> = musicDao.getAllMusic()
    val allVideos: LiveData<List<VideoEntity>> = videoDao.getAllVideos()
}