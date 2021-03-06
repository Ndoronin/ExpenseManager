package com.example.expensemanager.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "expense_table")
data class Expense (

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val amount: Int,
    val category: String,
    val date: String,
    val note: String
    ):Parcelable
{
    constructor(amount: Int,category: String, date: String,note: String): this(0,amount, category,date,note) { }
}