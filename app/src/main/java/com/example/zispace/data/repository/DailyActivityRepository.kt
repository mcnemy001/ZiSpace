package com.example.zispace.data.repository

import androidx.lifecycle.LiveData
import com.example.zispace.data.dao.DailyActivityDao
import com.example.zispace.data.dao.FriendDao
import com.example.zispace.data.entity.DailyActivityEntity
import com.example.zispace.data.entity.FriendEntity

class DailyActivityRepository(
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025


    private val dailyActivityDao: DailyActivityDao,
    private val friendDao: FriendDao
) {
    val allActivities: LiveData<List<DailyActivityEntity>> = dailyActivityDao.getAllActivities()
    val allFriends: LiveData<List<FriendEntity>> = friendDao.getAllFriends()
}