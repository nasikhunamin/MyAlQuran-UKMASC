package id.ac.um.ukm.asc.myalquran

import com.google.gson.annotations.SerializedName

data class Surah(
    @SerializedName("arti")
    var arti: String?,
    @SerializedName("asma")
    var asma : String?,
    @SerializedName("ayat")
    var ayat : Int?,
    @SerializedName("nama")
    var nama : String?,
    @SerializedName("type")
    var type : String?,
    @SerializedName("urut")
    var urut : String?,
    @SerializedName("audio")
    var audio : String?,
    @SerializedName("nomor")
    var nomor : String?,
    @SerializedName("rukuk")
    var rukuk : String?,
    @SerializedName("keterangan")
    var keterangan : String?
)