package com.example.expensemanager


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.model.Expense

class CustomRecyclerAdapter(/*private  val expenses: List<Expense>*/):
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>(){

    private var expenseList = emptyList<Expense>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val largeTextView: TextView = itemView.findViewById(R.id.textViewLarge)
        val smallTextView: TextView = itemView.findViewById(R.id.textViewSmall)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       /* holder.largeTextView.text = expenses[position].amount.toString()
        holder.smallTextView.text = expenses[position].category*/
        val currentItem = expenseList[position]
        holder.smallTextView.text = currentItem.amount.toString()
        holder.largeTextView.text = currentItem.category

    }

    fun setData (expenses: List<Expense>) {
        this.expenseList = expenses
        notifyDataSetChanged()
    }

    override fun getItemCount() = expenseList.size
    //override fun getItemCount() = expenses.size
}