package com.example.expensemanager.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.expensemanager.R
import com.example.expensemanager.databinding.FragmentExpenseEditBinding
import com.example.expensemanager.model.Expense
import com.example.expensemanager.viewmodel.ExpensesViewModel

class ExpenseEditFragment: Fragment(R.layout.fragment_expense_edit)  {
    private val binding: FragmentExpenseEditBinding by viewBinding()
    private val viewModel: ExpensesViewModel by activityViewModels()
    private var category: String = "Other"

    private val args by navArgs<ExpenseEditFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.amount.setText(args.currentExpense.amount.toString())
        category = args.currentExpense.category
        when(category){
            "Food and drinks" -> binding.categoryButtonGroup.selectButton(binding.categoryFoodAndDrinks)
            "Shopping" -> binding.categoryButtonGroup.selectButton(binding.categoryShopping)
            "Transport" -> binding.categoryButtonGroup.selectButton(binding.categoryTransport)
            "Leisure" -> binding.categoryButtonGroup.selectButton(binding.categoryLeisure)
            "Health" -> binding.categoryButtonGroup.selectButton(binding.categoryHealth)
            "Utilities" -> binding.categoryButtonGroup.selectButton(binding.categoryUtilities)
            else -> category ="Other"
        }

        binding.categoryButtonGroup.setOnSelectListener {
            category = it.text
        }

        binding.fabUpdate.setOnClickListener {
            updateExpense()
        }
    }

    private fun updateExpense(){
        val amount = binding.amount.text.toString()

        if(inputCheck(amount)){
            val expense = Expense(amount.toInt(),category,"","")
            expense.id = args.currentExpense.id
            viewModel.updateExpense(expense)
            Toast.makeText(requireContext(), "Expense updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_expenseEditFragment_to_FirstFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out amount", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(amount: String): Boolean{
        return !(TextUtils.isEmpty(amount) )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            viewModel.deleteExpense(args.currentExpense)
            Toast.makeText(requireContext(),"Expense successfully deleted",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_expenseEditFragment_to_FirstFragment)
        }
        builder.setNegativeButton("No"){_,_ ->
        }
        builder.setTitle("Delete an expense?")
        builder.setMessage("Are you sure you want to delete this expense?")
        builder.create().show()
    }
}