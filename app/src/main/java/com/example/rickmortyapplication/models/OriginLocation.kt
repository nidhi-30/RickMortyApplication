package com.example.rickmortyapplication.models

import com.google.gson.annotations.SerializedName

data class OriginLocation(
    @SerializedName("name")
    var name : String,
    @SerializedName("url")
    var url : String
)
