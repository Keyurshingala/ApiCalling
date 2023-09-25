package com.example.vasupractice.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Meme(
    @SerializedName("box_count")
    @Expose
    val boxCount: Int,
    @SerializedName("captions")
    @Expose
    val captions: Int,
    @SerializedName("height")
    @Expose
    val height: Int,
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("url")
    @Expose
    val url: String,
    @SerializedName("width")
    @Expose
    val width: Int
)
