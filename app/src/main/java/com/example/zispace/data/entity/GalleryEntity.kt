package com.example.zispace.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gallery_table")
data class GalleryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageName: String
)
