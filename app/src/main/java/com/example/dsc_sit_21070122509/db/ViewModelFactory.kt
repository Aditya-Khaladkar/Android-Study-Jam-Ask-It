package com.example.dsc_sit_21070122509.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val repository: ExpenseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExpenseViewModel(repository) as T
    }
}