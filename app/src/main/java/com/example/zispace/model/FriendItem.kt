package com.example.zispace.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FriendItem(
    val name: String,
    val avatar: String,
    val status: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null
) : Parcelable