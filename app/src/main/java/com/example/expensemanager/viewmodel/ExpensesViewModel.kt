package com.example.expensemanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemanager.model.Expense
import com.example.expensemanager.model.ExpenseDatabase
import com.example.expensemanager.model.ExpenseRepository
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

    var expenses = mutableListOf(
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
        Expense(3,"Groceries"),
    )
    /*
    fun addExpense(exp:Expense) {
        this.expenses.add(exp)
    }*/

    fun getTotal() :Int{
        var total = 0
        expenses.forEach {
            total+=it.amount
        }
        return total
    }

}