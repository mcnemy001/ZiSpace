package com.example.zispace.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zispace.data.entity.MusicEntity
import com.example.zispace.data.entity.VideoEntity

@Dao
interface MusicDao {
    @Query("SELECT * FROM music_table")
    fun getAllMusic(): LiveData<List<MusicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(music: List<MusicEntity>)
}

@Dao
interface VideoDao {
    @Query("SELECT * FROM video_table")
    fun getAllVideos(): LiveData<List<VideoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(videos: List<VideoEntity>)
}
