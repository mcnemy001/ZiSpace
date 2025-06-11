package com.example.zispace.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zispace.data.repository.ProfileRepository

class ProfileViewModelFactory(private val repository: ProfileRepository) : ViewModelProvider.Factory {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
