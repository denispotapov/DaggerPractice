package com.example.daggerpractice.models

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("id") private val id: Int,
                @SerializedName("username") private val username: String,
                @SerializedName("email") private val email: String,
                @SerializedName("website") private val website: String) {
}