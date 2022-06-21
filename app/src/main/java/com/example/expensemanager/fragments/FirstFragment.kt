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
import com.example.expensemanager.model.Expense
import com.example.expensemanager.viewmodel.ExpensesViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class FirstFragment : Fragment(R.layout.fragment_first){

    private val binding: FragmentFirstBinding by viewBinding()
    private lateinit var viewModel: ExpensesViewModel
    private var currentSpinnerText = "This Week"
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    private var today = simpleDateFormat.format(MaterialDatePicker.todayInUtcMilliseconds())


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
        setUpFragment(currentSpinnerText)
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                when(pos){
                    0 -> currentSpinnerText = "This week"
                    1 -> currentSpinnerText = "This month"
                    2 -> currentSpinnerText = "This year"
                    3 -> currentSpinnerText = "Overall"
                    4 -> currentSpinnerText = "Today"
                }
                setUpFragment(currentSpinnerText)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
      //  val spinner = Spinner(this)


        binding.recyclerView.setOnClickListener {

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

    private fun setUpFragment(spinnerText: String){
        //recycler view
        val adapter = RecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter
        val expenseList = mutableListOf<Expense>()
        //view model
        var total = 0
        viewModel = ViewModelProvider(this)[ExpensesViewModel::class.java]
        viewModel.readAllData.observe(viewLifecycleOwner) { expenses ->

            when(spinnerText){
                "This week" -> {
                    expenses.forEach {
                       if(checkWeek(today,it.date)) {
                           expenseList.add(it)
                           total+=it.amount
                       }
                    }
                }
                "This month" -> {
                    expenses.forEach {
                        if(checkMonth(today,it.date)) {
                            expenseList.add(it)
                            total+=it.amount
                        }
                    }
                }
                "This year" -> {
                    expenses.forEach {
                        if(checkYear(today,it.date)) {
                            expenseList.add(it)
                            total+=it.amount
                        }
                    }
                }
                "Overall" -> {
                    expenses.forEach {
                        expenseList.add(it)
                        total+=it.amount
                    }
                }
                "Today" -> {
                    expenses.forEach {
                        if(today == it.date) {
                            expenseList.add(it)
                            total+=it.amount
                        }
                    }
                }
            }
            adapter.setData(expenseList)

            binding.total.text = total.toString()
        }
    }

    private fun checkYear(today: String, date: String): Boolean {
        val year1 = today[6].digitToInt() * 1000 + today[7].digitToInt() * 100 + today[8].digitToInt() * 10 + today[9].digitToInt()
        val year2 = date[6].digitToInt() * 1000 + date[7].digitToInt() * 100 + date[8].digitToInt() * 10 + date[9].digitToInt()
        return year2 == year1
    }

    private fun checkMonth(today: String, date: String): Boolean {
        val month1 = today[3].digitToInt()*10 + today[4].digitToInt()
        val month2 = date[3].digitToInt()*10 + date[4].digitToInt()
        return month2 == month1
    }

    private fun checkWeek(date1:String, date2:String): Boolean{
        val day1 = date1[0].digitToInt()*10 + date1[1].digitToInt()
        val day2 = date2[0].digitToInt()*10 + date2[1].digitToInt()
        val month1 = date1[3].digitToInt()*10 + date1[4].digitToInt()
        val month2 = date2[3].digitToInt()*10 + date2[4].digitToInt()
        if (month2 == month1)
            when(LocalDate.now().dayOfWeek.toString()){
                "Monday"->{
                    return (day2-day1)<=6 && (day2-day1)>=0
                }
                "Tuesday"->{
                    return (day2-day1)<=5  && (day2-day1)>=-1
                }
                "Wednesday"->{
                    return (day2-day1)<=4  && (day2-day1)>=-2
                }
                "Thursday"->{
                    return (day2-day1)<=3  && (day2-day1)>=-3
                }
                "Friday"->{
                    return (day2-day1)<=2  && (day2-day1)>=-4
                }
                "Saturday"->{
                    return (day2-day1)<=1  && (day2-day1)>=-5
                }
                "Sunday"->{
                    return (day2-day1)<=0  && (day2-day1)>=-6
                }
            }
        else
            return false
        return true
    }

}