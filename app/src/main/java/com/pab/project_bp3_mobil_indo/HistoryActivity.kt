package com.pab.project_bp3_mobil_indo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        val cardPesanan = findViewById<ConstraintLayout>(R.id.cardOrder)
        cardPesanan?.setOnClickListener {
            val intent = Intent(this, HistoryDetailActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        findViewById<ImageView>(R.id.btnBackHistory).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.navHome)?.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        findViewById<ImageView>(R.id.navSearch)?.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        findViewById<ImageView>(R.id.btnNavProfile)?.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
