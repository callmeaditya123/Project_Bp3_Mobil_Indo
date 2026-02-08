package com.pab.project_bp3_mobil_indo

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchActivity : AppCompatActivity() {

    private lateinit var adapterMobil: MobilAdapter
    private lateinit var daftarMobilAsli: List<Mobil>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)


        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)


        val scrollview = findViewById<androidx.core.widget.NestedScrollView>(R.id.searchScroll)
        ViewCompat.setOnApplyWindowInsetsListener(scrollview) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val btnNavHome = findViewById<ImageView>(R.id.navHome)
        btnNavHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val btnNavSell = findViewById<ImageView>(R.id.navSell)
        btnNavSell.setOnClickListener {
            val intent = Intent(this, SellActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val btnNavHistory = findViewById<ImageView>(R.id.btnNavHistory)
        btnNavHistory?.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val btnNavProfile = findViewById<ImageView>(R.id.btnNavProfile)
        btnNavProfile?.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        daftarMobilAsli = listOf(
            Mobil("BMW M4 Coupe F82", "Rp1,375,000,000", R.drawable.bmw_m4_merah),
            Mobil("BMW M4 Competition Coupe S58 G82", "Rp2,295,000,000", R.drawable.bmw_m4_gray),
            Mobil("BMW M4 Competition Coupe", "Rp1,750,000,000", R.drawable.bmw_m4_kuning),
            Mobil("BMW G82 M4 Competition Coupe", "Rp2,255,000,000", R.drawable.bmw_g82_biru),
            Mobil("BMW F32 435i convert M4 F82", "Rp799,000,000", R.drawable.bmw_f32_merah),
            Mobil("BMW G20 330i MSport", "Rp770,000,000", R.drawable.bmw_g20_black),
            Mobil("BMW M4 Competition", "Rp1,988,000,000", R.drawable.bmw_m4_hijau_stabilo),
            Mobil("BMW F30 320 Lci", "Rp360,000,000", R.drawable.bmw_f30_putih),
            Mobil("BMW M4 Competition Coupe", "Rp1,275,000,000", R.drawable.bmw_m4_kuning2),
            Mobil("BMW X3M Competition 2022", "Rp1,300,000,000", R.drawable.bmw_x3m),
            Mobil("Audi RS3 Quattro", "Rp2,200,000,000", R.drawable.audi_black),
            Mobil("Mercedes Benz CLA45 AMG", "Rp475,000,000", R.drawable.mercy_cla45),
            Mobil("Ferrari 458 Italia", "Rp5,500,000,000", R.drawable.ferrari_458),
            Mobil("Toyota Supra 3.0L MK5 AT", "Rp2,000,000,000", R.drawable.supra_mk5)
        )

        val rvMobil: RecyclerView = findViewById(R.id.rvCarSearch)
        rvMobil.layoutManager = LinearLayoutManager(this)
        adapterMobil = MobilAdapter(daftarMobilAsli.toMutableList())
        rvMobil.adapter = adapterMobil

        val chipAll = findViewById<TextView>(R.id.chipAll)
        val chipBMW = findViewById<TextView>(R.id.chipBMW)
        val chipAudi = findViewById<TextView>(R.id.chipAudi)
        val chipMercy = findViewById<TextView>(R.id.chipMercy)
        val chipFerrari = findViewById<TextView>(R.id.chipFerrari)
        val chipToyota = findViewById<TextView>(R.id.chipToyota)

        val allChips = listOf(chipAll, chipBMW, chipAudi, chipMercy, chipFerrari, chipToyota)

        fun setChipActive(activeChip: TextView) {
            allChips.forEach { chip ->
                if (chip == activeChip) {
                    chip.setBackgroundResource(R.drawable.bg_chip_active)
                    chip.setTextColor(getColor(R.color.black))
                    chip.setTypeface(null, android.graphics.Typeface.BOLD)
                } else {
                    chip.setBackgroundResource(R.drawable.bg_badge_gray)
                    chip.setTextColor(getColor(R.color.white))
                    chip.setTypeface(null, android.graphics.Typeface.NORMAL)
                }
            }
        }

        chipAll.setOnClickListener { setChipActive(chipAll); filterData("") }
        chipBMW.setOnClickListener { setChipActive(chipBMW); filterData("BMW") }
        chipAudi.setOnClickListener { setChipActive(chipAudi); filterData("Audi") }
        chipMercy.setOnClickListener { setChipActive(chipMercy); filterData("Mercedes") }
        chipFerrari.setOnClickListener { setChipActive(chipFerrari); filterData("Ferrari") }
        chipToyota.setOnClickListener { setChipActive(chipToyota); filterData("Toyota") }

        val etSearch: EditText = findViewById(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterData(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun filterData(query: String) {
        val filteredList = daftarMobilAsli.filter { mobil ->
            mobil.namaMobil?.contains(query, ignoreCase = true) ?: false
        }
        adapterMobil.updateData(filteredList)
    }
}
