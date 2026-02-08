package com.pab.project_bp3_mobil_indo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // GUNAKAN "USER_APP" agar sama dengan Login & Home
        val pref = getSharedPreferences("USER_APP", Context.MODE_PRIVATE)

        // Ambil status onboarding dan login
        val onboardingDone = pref.getBoolean("onboarding_done", false)
        val isLogin = pref.getBoolean("is_login", false)

        Handler(Looper.getMainLooper()).postDelayed({

            val intent = when {
                !onboardingDone -> {
                    // Jika baru pertama kali instal
                    Intent(this, OnboardingActivity::class.java)
                }
                isLogin -> {
                    // Jika sudah pernah login (Auto-Login)
                    Intent(this, HomeActivity::class.java)
                }
                else -> {
                    // Jika sudah onboarding tapi belum login
                    Intent(this, LoginActivity::class.java)
                }
            }

            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()

        }, 1000)
    }
}