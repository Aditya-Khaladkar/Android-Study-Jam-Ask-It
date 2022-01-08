package com.example.dsc_sit_21070122509.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_sit_21070122509.R
import com.example.dsc_sit_21070122509.db.Expense

class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    var expenseList = emptyList<Expense>()

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_expense_name = itemView.findViewById<TextView>(R.id.txt_expense_name)
        val txt_expense_price = itemView.findViewById<TextView>(R.id.txt_expense_price)
        val txt_expense_date = itemView.findViewById<TextView>(R.id.txt_expense_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.expense_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentItem = expenseList[position]
        holder.txt_expense_name.text = currentItem.expenseName
        holder.txt_expense_price.text = "Rs: ${currentItem.expensePrice}"
        holder.txt_expense_date.text = currentItem.expenseDate
    }

    fun setData(expense: List<Expense>) {
        this.expenseList = expense
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }
}