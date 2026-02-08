package com.pab.project_bp3_mobil_indo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    private lateinit var tvNamaUserHome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        tvNamaUserHome = findViewById(R.id.tvUserNamaHome)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tampilkanNama()


        findViewById<ImageView>(R.id.navSearch)?.setOnClickListener { pindahKeSearch() }
        findViewById<ImageView>(R.id.navSell)?.setOnClickListener { pindahKeSell() }
        findViewById<ImageView>(R.id.btnnavHistory)?.setOnClickListener { pindahKeHistory() }
        findViewById<ImageView>(R.id.btnNavProfile)?.setOnClickListener { pindahKeProfile() }

        findViewById<TextView>(R.id.tvLihatSemuaBrand)?.setOnClickListener { pindahKeSearch() }
        findViewById<TextView>(R.id.tvLihatSemuaKoleksi)?.setOnClickListener { pindahKeSearch() }

        setupBrandClicks()

        setupCarItems()
    }

    override fun onResume() {
        super.onResume()
        tampilkanNama()
    }

    private fun tampilkanNama() {
        val sharedPref = getSharedPreferences("USER_APP", Context.MODE_PRIVATE)
        val namaTersimpan = sharedPref.getString("NAMA_KEY", "User")
        tvNamaUserHome.text = "Halo, $namaTersimpan"
    }

    private fun setupBrandClicks() {

        findViewById<View>(R.id.brandBMW)?.setOnClickListener { pindahKeSearch() }
        findViewById<View>(R.id.brandAudi)?.setOnClickListener { pindahKeSearch() }
        findViewById<View>(R.id.brandMercy)?.setOnClickListener { pindahKeSearch() }
        findViewById<View>(R.id.brandFerrari)?.setOnClickListener { pindahKeSearch() }
        findViewById<View>(R.id.brandToyota)?.setOnClickListener { pindahKeSearch() }
    }

    private fun setupCarItems() {
        val itemBMW = findViewById<View>(R.id.itemCar1)
        itemBMW?.let {
            it.findViewById<TextView>(R.id.tvCarName).text = "BMW M4 Coupe F82"
            it.findViewById<ImageView>(R.id.imgCar).setImageResource(R.drawable.bmw)
            // Jika ingin klik mobil langsung ke Search juga:
            it.setOnClickListener { pindahKeSearch() }
        }

        val itemFerrari = findViewById<View>(R.id.itemCar2)
        itemFerrari?.let {
            it.findViewById<TextView>(R.id.tvCarName).text = "Ferrari 458 Italia"
            it.findViewById<ImageView>(R.id.imgCar).setImageResource(R.drawable.ferrari)
            it.findViewById<TextView>(R.id.tvSpeed).text = "340 KM/H"
            it.findViewById<TextView>(R.id.tvHP).text = "570 HP"
            // Jika ingin klik mobil langsung ke Search juga:
            it.setOnClickListener { pindahKeSearch() }
        }
    }


    private fun pindahKeSearch() {
        startActivity(Intent(this, SearchActivity::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun pindahKeSell() {
        startActivity(Intent(this, SellActivity::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun pindahKeHistory() {
        startActivity(Intent(this, HistoryActivity::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun pindahKeProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}