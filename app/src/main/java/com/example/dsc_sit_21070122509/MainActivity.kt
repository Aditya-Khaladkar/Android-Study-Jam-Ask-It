package com.example.dsc_sit_21070122509

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.dsc_sit_21070122509.intro.WelcomeScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(Runnable {
            intent = Intent(this, WelcomeScreen::class.java)
            startActivity(intent)
            finish()
        },2500)
    }
}