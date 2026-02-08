package com.pab.project_bp3_mobil_indo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        try {
            val nama = intent.getStringExtra("NAMA_MOBIL")
            val harga = intent.getStringExtra("HARGA_MOBIL")
            val gambar = intent.getIntExtra("GAMBAR_MOBIL", 0)

            val tvNamaHeader = findViewById<TextView>(R.id.tvNamaMobilHeader)
            val tvNamaDetail = findViewById<TextView>(R.id.tvNamaMobilDetail)
            val tvHarga = findViewById<TextView>(R.id.tvHargaMobil)
            val imgMobil = findViewById<ImageView>(R.id.imgMobil)
            val btnBack = findViewById<ImageView>(R.id.btnBack)
            val btnPesan = findViewById<AppCompatButton>(R.id.btnPesan)
            val btnShare = findViewById<ImageView>(R.id.btnShare) // Tambahkan inisialisasi share

            tvNamaHeader.text = nama ?: "Nama Mobil"
            tvNamaDetail.text = nama ?: "Nama Mobil"
            tvHarga.text = harga ?: "Harga tidak tersedia"

            if (gambar != 0) {
                imgMobil.setImageResource(gambar)
            }

            // --- Logika Tombol Back ---
            btnBack.setOnClickListener {
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }

            // --- Logika Tombol Share ---
            btnShare.setOnClickListener {
                val shareMessage = "Cek mobil keren ini di MobilIndo!\n\n" +
                        "Mobil: ${tvNamaHeader.text}\n" +
                        "Harga: ${tvHarga.text}\n\n" +
                        "Kondisi mantap, jangan sampai keduluan yang lain!"

                val intentShare = Intent(Intent.ACTION_SEND)
                intentShare.type = "text/plain"
                intentShare.putExtra(Intent.EXTRA_SUBJECT, "Info Mobil")
                intentShare.putExtra(Intent.EXTRA_TEXT, shareMessage)

                // Menampilkan menu pilihan aplikasi (WA, Telegram, dll)
                startActivity(Intent.createChooser(intentShare, "Bagikan ke:"))
            }

            // --- Logika Tombol Pesan ---
            btnPesan.setOnClickListener {
                val intentPayment = Intent(this, PaymentActivity::class.java)
                intentPayment.putExtra("NAMA_MOBIL", nama)
                intentPayment.putExtra("HARGA_MOBIL", harga)
                intentPayment.putExtra("GAMBAR_MOBIL", gambar)
                startActivity(intentPayment)

                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Terjadi kesalahan pada halaman detail", Toast.LENGTH_SHORT).show()
        }
    }
}