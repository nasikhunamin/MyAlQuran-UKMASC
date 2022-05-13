package com.example.myalquran.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.npoint.io/99c279bb173a6e28359c/"
    private var retrofit : Retrofit? = null
    private var apis : ApiServices? = null

    fun getApis () : ApiServices?{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        if (apis == null){
            apis = retrofit!!.create(ApiServices::class.java)
        }
        return apis
    }

}