package com.example.zispace.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileInfo(
    val name: String,
    val description: String,
    val profileImageUrl: String,
    val introduction: String
) : Parcelable
