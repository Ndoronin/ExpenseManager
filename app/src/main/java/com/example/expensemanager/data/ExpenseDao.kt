package com.example.expensemanager.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.expensemanager.model.Expense

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExpense(expense: Expense)


    @Query("DELETE FROM expense_table")
    suspend fun deleteAllExpenses()

    @Query("SELECT * FROM expense_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Expense>>

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)
}