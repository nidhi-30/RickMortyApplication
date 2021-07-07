package com.example.rickmortyapplication.models

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("info")
    var info : Info,
    @SerializedName("results")
    var results : ArrayList<Character>
)
