package com.pab.project_bp3_mobil_indo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val pref = getSharedPreferences("app_pref", MODE_PRIVATE)
        val onboardingDone = pref.getBoolean("onboarding_done", false)
        val isLogin = pref.getBoolean("is_login", false)

        Handler(Looper.getMainLooper()).postDelayed({

            val intent = when {
                !onboardingDone -> {
                    Intent(this, OnboardingActivity::class.java)
                }
                isLogin -> {
                    Intent(this, HomeActivity::class.java)
                }
                else -> {
                    Intent(this, LoginActivity::class.java)
                }
            }

            startActivity(intent)

            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

            finish()

        }, 1000)
    }
}
