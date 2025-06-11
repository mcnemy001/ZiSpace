package com.example.zispace.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.zispace.data.entity.InterestEntity
import com.example.zispace.data.entity.ProfileEntity
import com.example.zispace.data.repository.ProfileRepository

class HomeViewModel(repository: ProfileRepository) : ViewModel() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    val userProfile: LiveData<ProfileEntity> = repository.profile

    val hobbies: LiveData<List<InterestEntity>> = userProfile.switchMap { profile: ProfileEntity? ->
        if (profile == null) {
            MutableLiveData<List<InterestEntity>>().apply { value = emptyList() }
        } else {
            repository.getInterestsByCategory(profile.id, "hobby")
        }
    }

    val favoriteFood: LiveData<List<InterestEntity>> = userProfile.switchMap { profile: ProfileEntity? ->
        if (profile == null) {
            MutableLiveData<List<InterestEntity>>().apply { value = emptyList() }
        } else {
            repository.getInterestsByCategory(profile.id, "food")
        }
    }

    val favoriteDrinks: LiveData<List<InterestEntity>> = userProfile.switchMap { profile: ProfileEntity? ->
        if (profile == null) {
            MutableLiveData<List<InterestEntity>>().apply { value = emptyList() }
        } else {
            repository.getInterestsByCategory(profile.id, "drink")
        }
    }

    val interests: LiveData<List<InterestEntity>> = userProfile.switchMap { profile: ProfileEntity? ->
        if (profile == null) {
            MutableLiveData<List<InterestEntity>>().apply { value = emptyList() }
        } else {
            repository.getInterestsByCategory(profile.id, "interest")
        }
    }

    val aspirations: LiveData<List<InterestEntity>> = userProfile.switchMap { profile: ProfileEntity? ->
        if (profile == null) {
            MutableLiveData<List<InterestEntity>>().apply { value = emptyList() }
        } else {
            repository.getInterestsByCategory(profile.id, "aspiration")
        }
    }
}