package com.example.expensemanager


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.databinding.FragmentFirstBinding
import com.example.expensemanager.viewmodel.ExpensesViewModel


class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding: FragmentFirstBinding by viewBinding()
    private lateinit var viewModel: ExpensesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //recycler view
        val adapter = CustomRecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        //view model
        viewModel = ViewModelProvider(this)[ExpensesViewModel::class.java]
        viewModel.readAllData.observe(viewLifecycleOwner) { expense ->
            adapter.setData(expense)
        }



        binding.total.text = getString(R.string.totalExpense,viewModel.getTotal().toString())

        binding.fab.setOnClickListener {
            //TODO:open fix hiding fab
            findNavController().navigate(R.id.action_FirstFragment_to_addExpenseFragment6)
        }

    }

}