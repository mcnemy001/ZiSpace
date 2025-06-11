package com.example.zispace.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    // Data untuk Home & Profile
    val name: String,
    @ColumnInfo(name = "status_description") val description: String,
    @ColumnInfo(name = "profile_image_url") val profileImageUrl: String,

    // Data tambahan khusus halaman Profile
    @ColumnInfo(name = "about_me") val introduction: String,
    val phone: String,
    val email: String,
    val instagram: String,
    val location: String,
    val mapsUrl: String
)
