package com.pab.project_bp3_mobil_indo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvNama: TextView
    private lateinit var tvEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        tvNama = findViewById(R.id.tvNameUser)
        tvEmail = findViewById(R.id.tvEmailUser)
        val mainView = findViewById<ConstraintLayout>(R.id.mainProfile)

        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadData()


        findViewById<ImageView>(R.id.btnEditProfile)?.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("NAMA_SAAT_INI", tvNama.text.toString())
            intent.putExtra("EMAIL_SAAT_INI", tvEmail.text.toString())
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }


        findViewById<ImageView>(R.id.navHome)?.setOnClickListener {
            startActivity(
                Intent(this, HomeActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }


        findViewById<ImageView>(R.id.navSearch)?.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }


        findViewById<ImageView>(R.id.btnnavHistory)?.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }


        findViewById<LinearLayout>(R.id.btnKeluar)?.setOnClickListener {


            getSharedPreferences("USER_APP", Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply()

            getSharedPreferences("app_pref", MODE_PRIVATE)
                .edit()
                .putBoolean("is_login", false)
                .apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        val sharedPref = getSharedPreferences("USER_APP", Context.MODE_PRIVATE)
        val nama = sharedPref.getString("NAMA_KEY", "Aditya Ramadhan")
        val email = sharedPref.getString("EMAIL_KEY", "aditya123@gmail.com")
        tvNama.text = nama
        tvEmail.text = email
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        updateDataDariIntent(intent)
    }

    private fun updateDataDariIntent(intent: Intent?) {
        val namaBaru = intent?.getStringExtra("EXTRA_NAMA")
        val emailBaru = intent?.getStringExtra("EXTRA_EMAIL")
        if (!namaBaru.isNullOrEmpty()) tvNama.text = namaBaru
        if (!emailBaru.isNullOrEmpty()) tvEmail.text = emailBaru
    }
}
