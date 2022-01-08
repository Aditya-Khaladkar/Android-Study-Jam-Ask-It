package com.example.dsc_sit_21070122509.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dsc_sit_21070122509.R
import com.example.dsc_sit_21070122509.fragments.CaptureFragment
import com.example.dsc_sit_21070122509.fragments.ExpensesFragment
import com.example.dsc_sit_21070122509.fragments.NewsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {

    var presstime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val captureFragment = CaptureFragment()
        val expensesFragment = ExpensesFragment()
        val newsFragment = NewsFragment()

        makeCurrentFragment(expensesFragment)

        findViewById<BottomNavigationView>(R.id.bottom_nav_view)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_home -> makeCurrentFragment(captureFragment)
                    R.id.nav_expense -> makeCurrentFragment(expensesFragment)
                    R.id.nav_news -> makeCurrentFragment(newsFragment)
                }
                true
            }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.dashboard_screen, fragment)
            commit()
        }
    }

    override fun onBackPressed() {
        if (presstime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
        presstime = System.currentTimeMillis()
    }
}