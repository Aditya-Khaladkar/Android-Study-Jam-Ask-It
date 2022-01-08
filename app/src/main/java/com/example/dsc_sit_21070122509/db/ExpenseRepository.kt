package com.example.dsc_sit_21070122509.db

class ExpenseRepository(private val db: ExpenseDatabase) {

    fun insert(item: Expense) = db.expenseDao().addExpense(item)
    fun readExpenses() = db.expenseDao().getExpense()
    fun getTotalPrice() = db.expenseDao().getTotalPrice()
}