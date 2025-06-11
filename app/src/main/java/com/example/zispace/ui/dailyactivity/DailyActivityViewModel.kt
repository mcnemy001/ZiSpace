package com.example.zispace.ui.dailyactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zispace.data.entity.DailyActivityEntity
import com.example.zispace.data.entity.FriendEntity
import com.example.zispace.data.repository.DailyActivityRepository
import com.example.zispace.model.ActivityItem
import com.example.zispace.model.FriendItem

class DailyActivityViewModel(repository: DailyActivityRepository) : ViewModel() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    // Ambil data langsung dari repository
    val dailyActivities: LiveData<List<DailyActivityEntity>> = repository.allActivities
    val friends: LiveData<List<FriendEntity>> = repository.allFriends

}