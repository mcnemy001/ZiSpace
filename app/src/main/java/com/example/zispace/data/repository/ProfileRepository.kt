package com.example.zispace.data.repository

import androidx.lifecycle.LiveData
import com.example.zispace.data.dao.InterestDao
import com.example.zispace.data.dao.ProfileDao
import com.example.zispace.data.entity.InterestEntity
import com.example.zispace.data.entity.ProfileEntity

class ProfileRepository(
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    private val profileDao: ProfileDao,
    private val interestDao: InterestDao
) {
    // Mengambil data profil sebagai LiveData
    val profile: LiveData<ProfileEntity> = profileDao.getProfile()

    // Mengambil daftar minat berdasarkan kategori
    fun getInterestsByCategory(profileId: Int, category: String): LiveData<List<InterestEntity>> {
        return interestDao.getInterestsByCategory(profileId, category)
    }
}
