package com.example.expensemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class Expense (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val amount: Int,
    val category: String,
    )
{
    constructor(amount: Int,category: String): this(0,amount, category) { }
}