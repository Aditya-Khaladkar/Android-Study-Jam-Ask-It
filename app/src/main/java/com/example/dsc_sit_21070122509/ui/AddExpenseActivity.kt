package com.example.dsc_sit_21070122509.ui

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dsc_sit_21070122509.R
import com.example.dsc_sit_21070122509.db.*

class AddExpenseActivity : AppCompatActivity() {
    lateinit var ed_expenseName: EditText
    lateinit var ed_expensePrice: EditText
    lateinit var calendarView: CalendarView
    lateinit var ed_expenseDescription: EditText
    lateinit var date: String

    private lateinit var mExpenseViewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        val factory = ViewModelFactory(ExpenseRepository(ExpenseDatabase.getDatabase(this)))

        mExpenseViewModel = ViewModelProvider(this, factory).get(ExpenseViewModel::class.java)

        ed_expenseName = findViewById(R.id.ed_expenseName)
        ed_expensePrice = findViewById(R.id.ed_expensePrice)
        calendarView = findViewById(R.id.calendarView)
        ed_expenseDescription = findViewById(R.id.ed_expenseDescription)


        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            ("" + day + "/" + (month + 1) + "/" + year).also { date = it }

        }

        findViewById<Button>(R.id.btn_addExpense).setOnClickListener {
            val expense_name: String = ed_expenseName.text.toString()
            val expense_price: Double = ed_expensePrice.text.toString().toDouble()
            val expense_description: String = ed_expenseDescription.text.toString()

            val expense = Expense(0, expense_name, expense_price, date, expense_description)
            mExpenseViewModel.insert(expense)
            Toast.makeText(this, "Expense added", Toast.LENGTH_LONG).show()
        }
    }
}