package com.pab.project_bp3_mobil_indo

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MobilAdapter(private var listMobil: List<Mobil>) :
    RecyclerView.Adapter<MobilAdapter.MobilViewHolder>() {

    class MobilViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvCarName)
        val tvHarga: TextView = view.findViewById(R.id.tvCarPrice)
        val imgMobil: ImageView = view.findViewById(R.id.imgCar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobilViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car_search_result, parent, false)
        return MobilViewHolder(view)
    }

    override fun onBindViewHolder(holder: MobilViewHolder, position: Int) {
        val mobil = listMobil[position]

        holder.tvNama.text = mobil.namaMobil ?: "Nama tidak tersedia"
        holder.tvHarga.text = mobil.harga ?: "Harga tidak tersedia"

        // Proteksi jika gambar tidak ditemukan agar tidak crash
        try {
            holder.imgMobil.setImageResource(mobil.gambar)
        } catch (e: Exception) {
            // Jika error, pasang gambar placeholder atau biarkan kosong
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("NAMA_MOBIL", mobil.namaMobil)
                putExtra("HARGA_MOBIL", mobil.harga)
                putExtra("GAMBAR_MOBIL", mobil.gambar)
            }

            context.startActivity(intent)

            // Bungkus dalam try-catch agar jika folder res/anim kosong tidak Force Close
            if (context is Activity) {
                try {
                    context.overridePendingTransition(
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    // Fungsi update data yang lebih aman
    fun updateData(newList: List<Mobil>) {
        this.listMobil = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listMobil.size
}