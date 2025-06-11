package com.example.zispace.ui.dailyactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zispace.data.repository.DailyActivityRepository

class DailyActivityViewModelFactory(private val repository: DailyActivityRepository) : ViewModelProvider.Factory {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DailyActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
