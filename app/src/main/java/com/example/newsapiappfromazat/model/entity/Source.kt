package com.example.newsapiappfromazat.model.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String
)