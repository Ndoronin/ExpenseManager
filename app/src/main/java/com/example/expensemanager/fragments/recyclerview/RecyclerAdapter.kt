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

class RecyclerAdapter:
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

    private var expenseList = emptyList<Expense>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = expenseList[position]
        holder.amountTextView.text = currentItem.amount.toString()
        holder.categoryTextView.text = currentItem.category
        holder.rowLayout.setOnClickListener {

            holder.itemView.findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToExpenseEditFragment(currentItem))
        }
    }

    fun setData (expenses: List<Expense>) {
        this.expenseList = expenses
        notifyDataSetChanged()
    }

    override fun getItemCount() = expenseList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categoryTextView: TextView = itemView.findViewById(R.id.text_category)
        val amountTextView: TextView = itemView.findViewById(R.id.text_amount)
        val rowLayout: CardView = itemView.findViewById(R.id.rowLayout)


    }





}