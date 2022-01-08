package com.example.dsc_sit_21070122509.db

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel(){

    fun insert(item: Expense) = GlobalScope.launch {
        repository.insert(item)
    }

    fun readExpenses() = repository.readExpenses()

    fun getTotalPrice() = repository.getTotalPrice()

}