package com.example.zispace.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.zispace.data.entity.ProfileEntity
import com.example.zispace.data.repository.ProfileRepository

class ProfileViewModel(repository: ProfileRepository) : ViewModel() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025


    val profileData: LiveData<ProfileEntity> = repository.profile
}