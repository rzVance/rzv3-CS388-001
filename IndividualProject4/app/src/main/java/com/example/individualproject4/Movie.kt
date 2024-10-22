package com.example.individualproject4

import com.google.gson.annotations.SerializedName

class Movie {// (books layout)

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("poster_path")
    var poster_path: String? = null

    @JvmField
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null

    @JvmField
    @SerializedName("overview")
    var overview: String? = null



}