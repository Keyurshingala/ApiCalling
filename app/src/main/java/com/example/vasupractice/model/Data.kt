package com.example.vasupractice.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("memes")
    @Expose
    val memes: List<Meme>
)