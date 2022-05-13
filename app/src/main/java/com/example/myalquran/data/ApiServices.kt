package com.example.myalquran.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("data")
    fun getSurah() : Call<ArrayList<Surah>>

    @GET("surat/{nomor_surat}")
    fun getAyat(@Path("nomor_surat") nomorSurat : String) : Call<ArrayList<Ayat>>
}