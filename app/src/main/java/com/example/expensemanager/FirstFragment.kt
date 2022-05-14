package com.example.expensemanager


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.databinding.FragmentFirstBinding
import com.example.expensemanager.viewmodel.ExpensesViewModel


class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding: FragmentFirstBinding by viewBinding()
    private val viewModel: ExpensesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = CustomRecyclerAdapter(viewModel.expenses)
        binding.total.text = getString(R.string.totalExpense,viewModel.getTotal().toString())
        binding.fab.setOnClickListener {
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //TODO:open fix hiding fab
            findNavController().navigate(R.id.action_FirstFragment_to_addExpenseFragment6)
            //binding.fab.hide()
            //.setAction("Action", null).show()
        }

    }

}