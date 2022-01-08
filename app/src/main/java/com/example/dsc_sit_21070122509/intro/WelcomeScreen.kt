package com.example.dsc_sit_21070122509.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.dsc_sit_21070122509.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        findViewById<FloatingActionButton>(R.id.btn_intro_next).setOnClickListener {
            startActivity(Intent(applicationContext, InstructionScreen::class.java))
            finish()
        }
    }
}