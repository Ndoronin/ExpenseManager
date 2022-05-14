package com.example.expensemanager.viewmodel

import androidx.lifecycle.ViewModel
import com.example.expensemanager.model.Expense

class ExpensesViewModel: ViewModel() {

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
    fun addExpense(exp:Expense) {
        this.expenses.add(exp)
    }

    fun getTotal() :Int{
        var total = 0
        expenses.forEach {
            total+=it.amount
        }
        return total
    }

}