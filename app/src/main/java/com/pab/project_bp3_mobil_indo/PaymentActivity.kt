package com.pab.project_bp3_mobil_indo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // animasi saat activity muncul
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        val nama = intent.getStringExtra("NAMA_MOBIL")
        val harga = intent.getStringExtra("HARGA_MOBIL")
        val gambar = intent.getIntExtra("GAMBAR_MOBIL", 0)

        findViewById<TextView>(R.id.tvNamaMobilPayment).text = nama
        findViewById<TextView>(R.id.tvTotalBayar).text = harga
        findViewById<ImageView>(R.id.imgMobilPayment).setImageResource(gambar)

        findViewById<ImageView>(R.id.btnBackPayment).setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val btnKonfirmasi = findViewById<AppCompatButton>(R.id.btnKonfirmasi)
        btnKonfirmasi.setOnClickListener {
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
