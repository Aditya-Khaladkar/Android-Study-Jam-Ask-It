package com.example.dsc_sit_21070122509.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_sit_21070122509.R
import com.example.dsc_sit_21070122509.adapters.ExpenseAdapter
import com.example.dsc_sit_21070122509.db.ExpenseDatabase
import com.example.dsc_sit_21070122509.db.ExpenseRepository
import com.example.dsc_sit_21070122509.db.ExpenseViewModel
import com.example.dsc_sit_21070122509.db.ViewModelFactory
import com.example.dsc_sit_21070122509.ui.AddExpenseActivity
import com.example.dsc_sit_21070122509.ui.TotalExpenseActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExpensesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExpensesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var mExpenseViewModel: ExpenseViewModel
    lateinit var expense_recyclerView: RecyclerView
    lateinit var txt_getTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val expenseView: View = inflater.inflate(R.layout.fragment_expenses, container, false)

        expense_recyclerView = expenseView.findViewById(R.id.expense_recyclerView)
        val adapter = ExpenseAdapter()
        expense_recyclerView.adapter = adapter
        expense_recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val factory = ViewModelFactory(ExpenseRepository(ExpenseDatabase.getDatabase(requireContext())))

        mExpenseViewModel = ViewModelProvider(this, factory).get(ExpenseViewModel::class.java)
        mExpenseViewModel.readExpenses().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        // delete expense
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Thread(Runnable {
                    val expenseDao = ExpenseDatabase.getDatabase(requireContext()).expenseDao()
                    expenseDao.deleteExpense(adapter.expenseList[viewHolder.adapterPosition])
                }).start()
            }
        }).attachToRecyclerView(expense_recyclerView)

        expenseView.findViewById<Button>(R.id.btn_getTotal).setOnClickListener {
            startActivity(Intent(context, TotalExpenseActivity::class.java))
        }

        expenseView.findViewById<FloatingActionButton>(R.id.btn_add).setOnClickListener {
            startActivity(Intent(context, AddExpenseActivity::class.java))
        }

        return expenseView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExpensesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExpensesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}