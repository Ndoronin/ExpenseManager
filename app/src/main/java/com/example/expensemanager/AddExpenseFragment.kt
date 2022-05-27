package com.example.expensemanager

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.expensemanager.databinding.FragmentAddExpenseBinding
import com.example.expensemanager.model.Expense
import com.example.expensemanager.viewmodel.ExpensesViewModel


class AddExpenseFragment : Fragment(R.layout.fragment_add_expense) {


    private val binding: FragmentAddExpenseBinding by viewBinding()
    private val viewModel: ExpensesViewModel by activityViewModels()
    private var category: String = "Other"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.categoryButtonGroup.setOnSelectListener {
            category = it.text
        }

        binding.fabSubmit.setOnClickListener {
            insertExpenseToDatabase()
        }

    }

    private fun insertExpenseToDatabase(){
        val amount = binding.amount.text.toString()

        if(inputCheck(amount)){
            val expense = Expense(amount.toInt(),category)
            viewModel.addExpense(expense)
            Toast.makeText(requireContext(), "Expense added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addExpenseFragment_to_FirstFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(amount: String): Boolean{
        return !(TextUtils.isEmpty(amount) )
    }



}