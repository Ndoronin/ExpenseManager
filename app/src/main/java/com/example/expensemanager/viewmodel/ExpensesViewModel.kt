package com.example.expensemanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.expensemanager.model.Expense
import com.example.expensemanager.data.ExpenseDatabase
import com.example.expensemanager.data.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpensesViewModel(application: Application): AndroidViewModel(application) {


    val readAllData: LiveData<List<Expense>>
    private val repository: ExpenseRepository


    init {
        val expenseDao = ExpenseDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
        readAllData = repository.readAllData
    }

    fun addExpense(expense: Expense)
    {
        viewModelScope.launch(Dispatchers.IO){
            repository.addExpense(expense)
        }
    }

    fun updateExpense(expense: Expense){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateExpense(expense)
        }
    }

    fun deleteAllExpenses(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllExpenses()
        }
    }



}