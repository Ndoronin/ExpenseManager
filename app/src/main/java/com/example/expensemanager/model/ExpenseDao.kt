package com.example.expensemanager.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExpense(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Expense>>
}