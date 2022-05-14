package com.example.expensemanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity
data class Expense (
    //@PrimaryKey(autoGenerate = true)
   // val id: Int,
    val amount: Int,
    val category: String
    )