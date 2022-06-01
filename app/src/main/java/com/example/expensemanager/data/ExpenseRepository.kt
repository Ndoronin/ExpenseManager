package com.example.expensemanager.data

import androidx.lifecycle.LiveData
import com.example.expensemanager.model.Expense

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val readAllData: LiveData<List<Expense>> = expenseDao.readAllData()

    suspend fun addExpense(expense: Expense){
        expenseDao.addExpense(expense)
    }
    suspend fun deleteAllExpenses(){
        expenseDao.deleteAllExpenses()
    }

    suspend fun updateExpense(expense: Expense){
        expenseDao.updateExpense(expense)
    }
}