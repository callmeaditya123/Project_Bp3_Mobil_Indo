package com.pab.project_bp3_mobil_indo

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.pab.project_bp3_mobil_indo.data.AppDatabase
import com.pab.project_bp3_mobil_indo.data.UserEntity
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private var isPassVisible = false
    private var isKonfPassVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        // animasi saat activity muncul
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        val etNamaLengkap = findViewById<EditText>(R.id.etNamaLengkap)
        val etEmailSignup = findViewById<EditText>(R.id.etEmailSignup)
        val etPass = findViewById<EditText>(R.id.etPassSignup)
        val etKonfPass = findViewById<EditText>(R.id.etKonfPass)

        val btnEyePass = findViewById<ImageView>(R.id.btnEyePass)
        val btnEyeKonfPass = findViewById<ImageView>(R.id.btnEyeKonfPass)
        val btnDaftar = findViewById<Button>(R.id.btn_daftar)
        val tvMasuk = findViewById<TextView>(R.id.tvMasuk)

        val db = AppDatabase.getInstance(this)

        btnEyePass.setOnClickListener {
            isPassVisible = !isPassVisible
            togglePassword(etPass, btnEyePass, isPassVisible)
        }

        btnEyeKonfPass.setOnClickListener {
            isKonfPassVisible = !isKonfPassVisible
            togglePassword(etKonfPass, btnEyeKonfPass, isKonfPassVisible)
        }

        tvMasuk.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }

        btnDaftar.setOnClickListener {

            val nama = etNamaLengkap.text.toString().trim()
            val email = etEmailSignup.text.toString().trim()
            val pass = etPass.text.toString()
            val konf = etKonfPass.text.toString()

            if (nama.isEmpty() || email.isEmpty() || pass.isEmpty() || konf.isEmpty()) {
                Toast.makeText(this, "Lengkapi semua data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass != konf) {
                Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {

                val emailExist = db.userDao().checkEmail(email)
                if (emailExist != null) {
                    runOnUiThread {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Email sudah terdaftar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return@launch
                }

                db.userDao().insertUser(
                    UserEntity(
                        nama = nama,
                        email = email,
                        password = pass
                    )
                )

                runOnUiThread {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Daftar berhasil",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(
                            this@SignUpActivity,
                            LoginActivity::class.java
                        )
                    )
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                }
            }
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

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun togglePassword(
        editText: EditText,
        icon: ImageView,
        visible: Boolean
    ) {
        if (visible) {
            editText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            icon.setImageResource(R.drawable.ic_eye)
        } else {
            editText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            icon.setImageResource(R.drawable.ic_eye_off)
        }
        editText.setSelection(editText.text.length)
    }
}
