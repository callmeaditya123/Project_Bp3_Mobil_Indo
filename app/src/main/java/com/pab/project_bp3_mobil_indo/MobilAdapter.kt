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

        holder.tvNama.text = mobil.namaMobil
        holder.tvHarga.text = mobil.harga
        holder.imgMobil.setImageResource(mobil.gambar)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("NAMA_MOBIL", mobil.namaMobil)
            intent.putExtra("HARGA_MOBIL", mobil.harga)
            intent.putExtra("GAMBAR_MOBIL", mobil.gambar)

            context.startActivity(intent)

            if (context is Activity) {
                context.overridePendingTransition(
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }
        }
    }

    fun updateData(newList: List<Mobil>) {
        listMobil = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listMobil.size
}
