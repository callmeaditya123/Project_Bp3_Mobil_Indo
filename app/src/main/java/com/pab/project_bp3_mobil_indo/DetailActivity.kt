package com.pab.project_bp3_mobil_indo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 1. Ambil data dari intent
        val nama = intent.getStringExtra("NAMA_MOBIL")
        val harga = intent.getStringExtra("HARGA_MOBIL")
        val gambar = intent.getIntExtra("GAMBAR_MOBIL", 0)

        // 2. Pasang ke View
        val tvNama = findViewById<TextView>(R.id.tvNamaMobil)
        val tvHarga = findViewById<TextView>(R.id.tvHargaMobil)
        val imgMobil = findViewById<ImageView>(R.id.imgMobil)

        tvNama.text = nama
        tvHarga.text = harga
        imgMobil.setImageResource(gambar)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<AppCompatButton>(R.id.btnPesan).setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("NAMA_MOBIL", nama)
            intent.putExtra("HARGA_MOBIL", harga)
            intent.putExtra("GAMBAR_MOBIL", gambar)
            startActivity(intent)
        }
    }
}