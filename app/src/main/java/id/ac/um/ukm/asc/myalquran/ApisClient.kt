package id.ac.um.ukm.asc.myalquran

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApisClient {
    private const val BASE_URL = "https://api.npoint.io/99c279bb173a6e28359c/"
    private var retrofit : Retrofit? = null
    private var apis : Apis? = null

    fun getApis () : Apis?{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        if (apis == null){
            apis = retrofit!!.create(Apis::class.java)
        }
        return apis
    }

}