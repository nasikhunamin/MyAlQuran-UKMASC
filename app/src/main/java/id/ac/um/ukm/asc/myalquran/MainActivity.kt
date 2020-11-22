package id.ac.um.ukm.asc.myalquran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val apis = ApisClient.getApis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLoading(true)
        apis!!.getSurah().enqueue(object : Callback<ArrayList<Surah>>{
            override fun onFailure(call: Call<ArrayList<Surah>>, t: Throwable) {
                showLoading(false)
                Log.e("ERROR", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<Surah>>,
                response: Response<ArrayList<Surah>>
            ) {
                if (response.isSuccessful){
                    showLoading(false)
                    rv_surah.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        adapter = SurahAdapter(this@MainActivity, response.body()!!)
                    }
                }
            }
        })
    }

    private fun showLoading(status : Boolean){
        if (status){
            pb_loading_surah.visibility = VISIBLE
            rv_surah.visibility = GONE
        }else{
            pb_loading_surah.visibility = GONE
            rv_surah.visibility = VISIBLE
        }
    }
}