package com.example.dsc_sit_21070122509.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class Expense (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    val expenseName: String,

    @ColumnInfo(name = "price")
    val expensePrice: Double,

    @ColumnInfo(name = "date")
    val expenseDate: String,

    @ColumnInfo(name = "description")
    val expenseDescription: String
)