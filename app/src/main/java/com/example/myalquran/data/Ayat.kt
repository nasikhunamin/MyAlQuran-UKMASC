package com.example.myalquran.data

import com.google.gson.annotations.SerializedName

data class Ayat(
    @SerializedName("ar")
    var ayatArab : String? = null,
    @SerializedName("id")
    var arti : String? = null,
    @SerializedName("nomor")
    var nomor : String? = null,
)
