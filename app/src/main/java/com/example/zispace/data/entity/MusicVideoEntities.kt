package com.example.zispace.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music_table")
data class MusicEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val artist: String,
    val albumArt: String,
    val url: String
)

@Entity(tableName = "video_table")
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val thumbnail: String,
    val url: String
)