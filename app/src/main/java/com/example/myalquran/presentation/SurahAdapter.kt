package com.example.myalquran.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myalquran.data.Surah
import com.example.myalquran.databinding.ItemSurahBinding

class SurahAdapter : RecyclerView.Adapter<SurahAdapter.ViewHolder>(){

    var surahList = mutableListOf<Surah>()
    set(value) {
        if (value.isNotEmpty()){
            field.clear()
        }

        field.addAll(value)
        notifyDataSetChanged()
    }

    var callback : SurahAdapterCallback? = null

    inner class ViewHolder(private val binding : ItemSurahBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(surah: Surah) {
            with(itemView){
                binding.txtNumber.text = surah.nomor.toString()
                binding.txtMeaning.text = surah.arti
                binding.txtTitle.text = surah.nama
                binding.txtTitleArabic.text = surah.asma
                setOnClickListener {
                    callback?.onSelectSurah(surah)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = surahList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(surahList[position])
    }

    interface SurahAdapterCallback{
        fun onSelectSurah(surah: Surah)
    }
}