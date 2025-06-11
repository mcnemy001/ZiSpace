package com.example.zispace.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zispace.data.entity.DailyActivityEntity
import com.example.zispace.data.entity.FriendEntity

@Dao
interface DailyActivityDao {
    @Query("SELECT * FROM daily_activity_table")
    fun getAllActivities(): LiveData<List<DailyActivityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(activities: List<DailyActivityEntity>)

    @Query("SELECT COUNT(*) FROM daily_activity_table")
    suspend fun getActivityCount(): Int
}

@Dao
interface FriendDao {
    @Query("SELECT * FROM friend_table")
    fun getAllFriends(): LiveData<List<FriendEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(friends: List<FriendEntity>)

    @Query("SELECT COUNT(*) FROM friend_table")
    suspend fun getFriendCount(): Int
}