package com.example.expensemanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.expensemanager.databinding.FragmentAddExpenseBinding
import com.example.expensemanager.model.Expense
import com.example.expensemanager.viewmodel.ExpensesViewModel


class AddExpenseFragment : Fragment(R.layout.fragment_add_expense) {


    private val binding: FragmentAddExpenseBinding by viewBinding()
    private val viewModel: ExpensesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener{
            //TODO CATCH INT -> EDITTEXT BUGS + SET CATEGORIES
            val expense = Expense( binding.amount.text.toString().toInt(),binding.category.text.toString())
            viewModel.addExpense(expense)
            findNavController().navigate(R.id.action_addExpenseFragment_to_FirstFragment)
        }
    }



}