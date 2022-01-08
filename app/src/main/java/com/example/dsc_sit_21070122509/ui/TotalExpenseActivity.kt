package com.example.dsc_sit_21070122509.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.dsc_sit_21070122509.R
import com.example.dsc_sit_21070122509.db.ExpenseDatabase

class TotalExpenseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_expense)

        var txt_getTotal = findViewById<TextView>(R.id.txt_getTotal)
        Thread(Runnable {
            val expenseDao = ExpenseDatabase.getDatabase(this).expenseDao()
            txt_getTotal.text = "Total ${expenseDao.getTotalPrice()} /-"
        }).start()
    }
}