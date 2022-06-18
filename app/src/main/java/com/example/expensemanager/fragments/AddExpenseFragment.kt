package com.example.expensemanager.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.expensemanager.R
import com.example.expensemanager.databinding.FragmentAddExpenseBinding
import com.example.expensemanager.model.Expense
import com.example.expensemanager.viewmodel.ExpensesViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class AddExpenseFragment : Fragment(R.layout.fragment_add_expense) {


    private val binding: FragmentAddExpenseBinding by viewBinding()
    private val viewModel: ExpensesViewModel by activityViewModels()
    private var category: String = "Other"
    private var selectedDate = MaterialDatePicker.todayInUtcMilliseconds()
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.editTextDate.setOnClickListener {
            val today = MaterialDatePicker.todayInUtcMilliseconds()
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date").setSelection(today).build()

            datePicker.addOnPositiveButtonClickListener {
                selectedDate = datePicker.selection!!
                val dateString = simpleDateFormat.format(selectedDate)
                binding.editTextDate.setText(dateString)
            }
            datePicker.show(childFragmentManager,"tag")


        }

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
            val expense = Expense(amount.toInt(),category,simpleDateFormat.format(selectedDate),binding.note.text.toString())
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