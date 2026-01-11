package com.pab.project_bp3_mobil_indo

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pab.project_bp3_mobil_indo.data.AppDatabase

class LoginActivity : AppCompatActivity() {

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnTogglePassword = findViewById<ImageView>(R.id.btnTogglePassword)
        val btnLogin = findViewById<Button>(R.id.btn_masuk)
        val tvDaftar = findViewById<TextView>(R.id.tvDaftar)

        val db = AppDatabase.getInstance(this)

        // 👁 Toggle Password
        btnTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                etPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                btnTogglePassword.setImageResource(R.drawable.ic_eye)
            } else {
                etPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                btnTogglePassword.setImageResource(R.drawable.ic_eye_off)
            }
            etPassword.setSelection(etPassword.text.length)
        }

        // 🔐 LOGIN
        btnLogin.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Email dan password wajib diisi",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val user = db.userDao().login(email, password)

            if (user != null) {


                getSharedPreferences("app_pref", MODE_PRIVATE)
                    .edit()
                    .putBoolean("is_login", true)
                    .putString("login_email", user.email) // 🔑 INI KUNCI MASALAHMU
                    .apply()

                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, HomeActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)


                overridePendingTransition(
                    R.anim.fade_in,
                    R.anim.fade_out
                )

            } else {
                Toast.makeText(
                    this,
                    "Email atau password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        tvDaftar.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            overridePendingTransition(
                R.anim.fade_in,
                R.anim.fade_out
            )
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }
}
