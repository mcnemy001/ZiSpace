package com.example.zispace.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "interest_table",
    foreignKeys = [ForeignKey(
        entity = ProfileEntity::class, // Pastikan ini merujuk ke ProfileEntity yang benar
        parentColumns = ["id"],
        childColumns = ["profile_owner_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class InterestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "profile_owner_id", index = true) val profileOwnerId: Int,
    val category: String,
    val name: String
)
