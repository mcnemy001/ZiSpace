package com.example.zispace.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table_profile")
data class ProfileInfoProfile(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    @ColumnInfo(name = "profile_image_url") val profileImageUrl: String,
    val introduction: String,

    // Kolom baru
    val phone: String,
    val email: String,
    val instagram: String,
    val location: String,
    val mapsUrl: String
)