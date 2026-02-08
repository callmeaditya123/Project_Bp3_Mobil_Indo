package com.pab.project_bp3_mobil_indo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SellActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)


        val navHome = findViewById<ImageView>(R.id.navHome)
        val navSearch = findViewById<ImageView>(R.id.navSearch)
        val navHistory = findViewById<ImageView>(R.id.btnNavHistory)
        val navProfile = findViewById<ImageView>(R.id.btnNavProfile)
        val btnDaftarkan = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnDaftarkan)


        navHome?.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }

        navSearch?.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        navHistory?.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        navProfile?.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        btnDaftarkan?.setOnClickListener {
            Toast.makeText(this, "Iklan Berhasil Didaftarkan!", Toast.LENGTH_SHORT).show()
        }
    }
}