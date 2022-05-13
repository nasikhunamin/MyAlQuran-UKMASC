package com.example.myalquran.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myalquran.data.ApiClient
import com.example.myalquran.data.Surah
import com.example.myalquran.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private val apis = ApiClient.getApis()

    private lateinit var binding : ActivityHomeBinding
    private var surahAdapter = SurahAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        initNetwork()
    }

    private fun initNetwork() {
        showLoading(true)
        apis!!.getSurah().enqueue(object : Callback<ArrayList<Surah>> {
            override fun onFailure(call: Call<ArrayList<Surah>>, t: Throwable) {
                showLoading(false)
                Log.e("ERROR", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<Surah>>,
                response: Response<ArrayList<Surah>>
            ) {
                if (response.isSuccessful){
                    val surah = response.body()
                    surahAdapter.surahList = surah?.toMutableList() ?: mutableListOf()

                    showLoading(false)
                }
            }
        })
    }

    private fun initUI() {
        binding.rvSurah.layoutManager = LinearLayoutManager(this@HomeActivity)
        binding.rvSurah.setHasFixedSize(true)
        binding.rvSurah.adapter = surahAdapter

        surahAdapter.callback = object : SurahAdapter.SurahAdapterCallback{
            override fun onSelectSurah(surah: Surah) {
                val ayatIntent = Intent(this@HomeActivity, AyatActivity::class.java)
                ayatIntent.putExtra(AyatActivity.EXTRA_SURAH, surah)
                startActivity(ayatIntent)
            }

        }
    }

    private fun showLoading(isLoading : Boolean){
        if (isLoading){
            binding.pbLoadingSurah.visibility = View.VISIBLE
            binding.rvSurah.visibility = View.GONE
        }else{
            binding.pbLoadingSurah.visibility = View.GONE
            binding.rvSurah.visibility = View.VISIBLE
        }
    }

}