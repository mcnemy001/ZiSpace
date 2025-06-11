package com.example.zispace.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zispace.data.entity.InterestEntity
import com.example.zispace.data.entity.ProfileEntity

@Dao
interface ProfileDao {
    // Memasukkan satu profil. Jika ada, akan diganti.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)

    // Mengambil profil pertama dari tabel (kita asumsikan hanya ada satu profil)
    @Query("SELECT * FROM profile_table LIMIT 1")
    fun getProfile(): LiveData<ProfileEntity>

    // Menghitung jumlah profil, digunakan untuk pre-populasi data
    @Query("SELECT COUNT(*) FROM profile_table")
    suspend fun getProfileCount(): Int
}

@Dao
interface InterestDao {
    // Memasukkan daftar minat
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(interests: List<InterestEntity>)

    // Mengambil semua minat berdasarkan kategori dan ID profil
    @Query("SELECT * FROM interest_table WHERE profile_owner_id = :profileId AND category = :category")
    fun getInterestsByCategory(profileId: Int, category: String): LiveData<List<InterestEntity>>
}