package com.example.expensemanager.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.viewbinding.library.fragment.viewBinding
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.R
import com.example.expensemanager.databinding.FragmentFirstBinding
import com.example.expensemanager.fragments.recyclerview.RecyclerAdapter
import com.example.expensemanager.viewmodel.ExpensesViewModel


class FirstFragment : Fragment(R.layout.fragment_first), AdapterView.OnItemSelectedListener {

    private val binding: FragmentFirstBinding by viewBinding()
    private lateinit var viewModel: ExpensesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val languages = arrayOf("This week", "This month", "This year", "Overall", "Today")
        val aa = ArrayAdapter(activity?.applicationContext!!,android.R.layout.simple_spinner_dropdown_item, languages)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = aa
        val spinner = binding.spinner
        spinner.onItemSelectedListener
      //  val spinner = Spinner(this)

        //recycler view
        val adapter = RecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        //view model
        var total = 0
        viewModel = ViewModelProvider(this)[ExpensesViewModel::class.java]
        viewModel.readAllData.observe(viewLifecycleOwner) { expenses ->
            adapter.setData(expenses)
            expenses.forEach {
                total+=it.amount
            }

            val totalStr = getString(R.string.totalExpense,total.toString()) +
                    " " +getString(R.string.Currency)
            binding.total.text = totalStr
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_addExpenseFragment6)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        inflater.inflate(R.menu.menu_main,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_delete_all)
            deleteAllExpenses()
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllExpenses() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteAllExpenses()
            Toast.makeText(
                requireContext(),
                "Successfully removed everything",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}