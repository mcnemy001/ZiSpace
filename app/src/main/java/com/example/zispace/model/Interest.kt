package com.example.zispace.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Interest(
    val name: String
) : Parcelable
