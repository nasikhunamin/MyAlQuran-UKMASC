package com.example.myalquran.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myalquran.data.Ayat
import com.example.myalquran.databinding.ItemAyatBinding

class AyatAdapter : RecyclerView.Adapter<AyatAdapter.ViewHolder>() {

    var ayatList = mutableListOf<Ayat>()
    set(value) {
        if (value.isNotEmpty()){

            field.clear()
        }

        field.addAll(value)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemAyatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ayat: Ayat) {
            with(itemView) {
                binding.txtNomorAyat.text = ayat.nomor
                binding.txtArtiAyat.text = ayat.arti
                binding.txtTeksArab.text = ayat.ayatArab
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAyatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = ayatList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ayatList[position])
    }
}