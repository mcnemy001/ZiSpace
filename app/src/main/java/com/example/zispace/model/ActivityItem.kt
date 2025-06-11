package com.example.zispace.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class ActivityItem(
    val icon: String,
    val name: String,
    val description: String? = null,
    val time: String? = null
) : Parcelable