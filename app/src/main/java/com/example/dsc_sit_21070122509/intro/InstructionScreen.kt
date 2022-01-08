package com.example.dsc_sit_21070122509.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_sit_21070122509.R
import com.example.dsc_sit_21070122509.adapters.IntroAdapter
import com.example.dsc_sit_21070122509.dashboard.DashboardActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InstructionScreen : AppCompatActivity() {
    lateinit var adapter: IntroAdapter
    private var list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction_screen)

        findViewById<FloatingActionButton>(R.id.btn_instruction_next).setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        var recyclerView = findViewById<RecyclerView>(R.id.introRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        list.add("I can detect objects")
        list.add("I can manage your expenses")
        list.add("I can keep you up to date on global affairs")

        adapter = IntroAdapter(list)
        recyclerView.adapter = adapter

    }
}