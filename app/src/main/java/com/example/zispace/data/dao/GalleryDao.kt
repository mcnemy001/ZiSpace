package com.example.zispace.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.example.zispace.data.entity.GalleryEntity

@Dao
interface GalleryDao {
    @Query("SELECT * FROM gallery_table")
    fun getAllImages(): LiveData<List<GalleryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<GalleryEntity>)

    @Query("SELECT COUNT(*) FROM gallery_table")
    suspend fun getImageCount(): Int
}
