package com.pab.project_bp3_mobil_indo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_profile)

        val etNama = findViewById<EditText>(R.id.etEditNama)
        val etEmail = findViewById<EditText>(R.id.etEditEmail)
        val btnSimpan = findViewById<AppCompatButton>(R.id.btnSimpanProfile)
        val btnBack = findViewById<ImageView>(R.id.btnBackEditProfile)

        etNama.setText(intent.getStringExtra("NAMA_SAAT_INI"))
        etEmail.setText(intent.getStringExtra("EMAIL_SAAT_INI"))

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainEditProfile)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        btnSimpan.setOnClickListener {
            val namaInput = etNama.text.toString()
            val emailInput = etEmail.text.toString()

            if (namaInput.isEmpty()) {
                etNama.error = "Nama harus diisi"
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("USER_APP", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("NAMA_KEY", namaInput)
            editor.putString("EMAIL_KEY", emailInput)
            editor.apply()

            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("EXTRA_NAMA", namaInput)
            intent.putExtra("EXTRA_EMAIL", emailInput)
            intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            )

            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }
}
