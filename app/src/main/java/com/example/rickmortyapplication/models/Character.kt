package com.example.rickmortyapplication.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("status")
    var status : String,
    @SerializedName("species")
    var species : String,
    @SerializedName("type")
    var type : String,
    @SerializedName("gender")
    var gender : String,
    @SerializedName("origin")
    var origin : OriginLocation,
    @SerializedName("location")
    var location : OriginLocation,
    @SerializedName("image")
    var image : String,
    @SerializedName("episode")
    var episode : ArrayList<String>,
    @SerializedName("url")
    var url : String,
    @SerializedName("created")
    var created : String
) : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
    }
}