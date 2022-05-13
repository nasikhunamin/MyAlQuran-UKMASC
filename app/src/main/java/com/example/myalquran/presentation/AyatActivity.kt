package com.example.myalquran.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myalquran.data.ApiClient
import com.example.myalquran.data.Ayat
import com.example.myalquran.data.Surah
import com.example.myalquran.databinding.ActivityAyatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AyatActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_SURAH = "extra_surah"
    }

    private lateinit var binding : ActivityAyatBinding

    private val apis = ApiClient.getApis()
    private val ayatAdapter = AyatAdapter()
    private var surah : Surah? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAyatBinding.inflate(layoutInflater)

        setContentView(binding.root)

        surah = intent.getParcelableExtra(EXTRA_SURAH)

        initUI()
        initNetwork()
    }

    private fun initNetwork() {
        showLoading(true)
        apis!!.getAyat(surah?.nomor ?: "").enqueue(object : Callback<ArrayList<Ayat>> {
            override fun onFailure(call: Call<ArrayList<Ayat>>, t: Throwable) {
                showLoading(false)
                Log.e("ERROR", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<Ayat>>,
                response: Response<ArrayList<Ayat>>
            ) {
                if (response.isSuccessful){
                    val ayat = response.body()
                    ayatAdapter.ayatList = ayat?.toMutableList() ?: mutableListOf()

                    showLoading(false)
                }
            }
        })
    }

    private fun initUI() {
        binding.txtNamaSurah.text = surah?.nama
        binding.txtArtiSurah.text = surah?.arti
        binding.txtKeteranganSurah.text = HtmlCompat.fromHtml(surah?.keterangan ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)

        binding.rvAyat.layoutManager = LinearLayoutManager(this)
        binding.rvAyat.setHasFixedSize(true)
        binding.rvAyat.adapter = ayatAdapter

        binding.tbAyat.setNavigationOnClickListener { finish() }
    }

    private fun showLoading(isLoading : Boolean){
        if (isLoading){
            binding.pbLoadingAyat.visibility = View.VISIBLE
            binding.rvAyat.visibility = View.GONE
        }else{
            binding.pbLoadingAyat.visibility = View.GONE
            binding.rvAyat.visibility = View.VISIBLE
        }
    }
}