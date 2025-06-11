package com.example.zispace.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Tabel untuk menyimpan daftar aktivitas harian
@Entity(tableName = "daily_activity_table")
data class DailyActivityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val icon: String,
    val name: String,
    val description: String? = null,
    val time: String? = null
)

// Tabel untuk menyimpan daftar teman
@Entity(tableName = "friend_table")
data class FriendEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val avatar: String
)
