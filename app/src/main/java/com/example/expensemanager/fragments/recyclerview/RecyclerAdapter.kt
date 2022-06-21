package com.example.expensemanager.fragments.recyclerview


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.R
import com.example.expensemanager.fragments.AddExpenseFragment
import com.example.expensemanager.fragments.FirstFragment
import com.example.expensemanager.fragments.FirstFragmentDirections
import com.example.expensemanager.model.Expense
import java.util.Collections.swap
import java.util.stream.IntStream.range

class RecyclerAdapter:
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

    private var expenseList = emptyList<Expense>()
    private var totalForCategories = arrayOf(0,0,0,0,0,0,0)
    private var totalCount = 0
    private val recyclerItems = mutableListOf<ExpensesForCategory>()

    inner class ExpensesForCategory(var category: String, var amount: Int) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)




        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


       // val currentItem = expenseList[position]
        val currentItem = recyclerItems[position]
        //holder.amountTextView.text = currentItem.amount.toString() +" "+  currentItem.date + " "+ currentItem.note
        holder.categoryTextView.text = currentItem.category
        holder.amountTextView.text = currentItem.amount.toString()
       /* holder.rowLayout.setOnClickListener {

            holder.itemView.findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToExpenseEditFragment(currentItem))
        }*/
    }

    fun setData (expenses: List<Expense>) {
        this.expenseList = expenses
        for ( i: Int in expenseList.indices){
            when(expenseList[i].category){
                "Food and drinks" -> totalForCategories[0]+=expenseList[i].amount
                "Shopping" -> totalForCategories[1]+=expenseList[i].amount
                "Transport" -> totalForCategories[2]+=expenseList[i].amount
                "Leisure" -> totalForCategories[3]+=expenseList[i].amount
                "Health" -> totalForCategories[4]+=expenseList[i].amount
                "Utilities" -> totalForCategories[5]+=expenseList[i].amount
                "Other" -> totalForCategories[6]+=expenseList[i].amount
            }
        }


        // holder.amountTextView.text = currentItem.amount.toString()
        for(i:Int in totalForCategories.indices){

            if(totalForCategories[i] > 0) {
                totalCount++
                when(i) {
                    0 -> recyclerItems.add(ExpensesForCategory("Food and drinks",totalForCategories[0]))
                    1 -> recyclerItems.add(ExpensesForCategory("Shopping",totalForCategories[1]))
                    2 -> recyclerItems.add(ExpensesForCategory("Transport",totalForCategories[2]))
                    3 -> recyclerItems.add(ExpensesForCategory("Leisure",totalForCategories[3]))
                    4 -> recyclerItems.add(ExpensesForCategory("Health",totalForCategories[4]))
                    5 -> recyclerItems.add(ExpensesForCategory("Utilities",totalForCategories[5]))
                    6 -> recyclerItems.add(ExpensesForCategory("Other",totalForCategories[6]))
                }
            }
        }

        //Sort
        for (i:Int in recyclerItems.indices)
            for(j:Int in 0 until (recyclerItems.size - i -1))
                if (recyclerItems[j].amount < recyclerItems[j+1].amount){
                    val tCategory = recyclerItems[j].category
                    val tAmount = recyclerItems[j].amount
                    recyclerItems[j].category = recyclerItems[j+1].category
                    recyclerItems[j].amount = recyclerItems[j+1].amount
                    recyclerItems[j+1].category = tCategory
                    recyclerItems[j+1].amount = tAmount
                }




        notifyDataSetChanged()
    }

   // override fun getItemCount() = expenseList.size
    override fun getItemCount() = totalCount

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categoryTextView: TextView = itemView.findViewById(R.id.text_category)
        val amountTextView: TextView = itemView.findViewById(R.id.text_amount)
        val rowLayout: CardView = itemView.findViewById(R.id.rowLayout)


    }





}