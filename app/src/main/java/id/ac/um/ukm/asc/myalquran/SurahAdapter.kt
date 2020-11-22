package id.ac.um.ukm.asc.myalquran

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_surah.view.*

class SurahAdapter (private val context : Context, private val surahList : ArrayList<Surah>)
    : RecyclerView.Adapter<SurahAdapter.ViewHolder>(){
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(surah: Surah) {
            with(itemView){
                txt_number.text = surah.nomor.toString()
                txt_meaning.text = surah.arti
                txt_title.text = surah.nama
                txt_title_arabic.text = surah.asma
                setOnClickListener {
                    Snackbar.make(itemView.rootView, "${surah.nama}", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_surah, parent, false))
    }

    override fun getItemCount(): Int = surahList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(surahList[position])
    }
}