package com.example.dsc_sit_21070122509.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDao {

    @Query("select * from expense_table")
    fun getExpense() : LiveData<List<Expense>>

    @Insert
    fun addExpense(expense: Expense)

    @Query("select sum(price) from expense_table")
    fun getTotalPrice(): Int

    @Delete
    fun deleteExpense(id: Expense)

}