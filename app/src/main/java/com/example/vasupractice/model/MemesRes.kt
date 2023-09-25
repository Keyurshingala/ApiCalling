package com.example.vasupractice.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MemesRes(
    @SerializedName("data")
    @Expose
    val data: Data,
    @SerializedName("success")
    @Expose
    val success: Boolean
)