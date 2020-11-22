package id.ac.um.ukm.asc.myalquran

import retrofit2.Call
import retrofit2.http.GET

interface Apis {
    @GET("data")
    fun getSurah() : Call<ArrayList<Surah>>
}