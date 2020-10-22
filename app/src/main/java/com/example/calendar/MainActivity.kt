package com.example.calendar

import android.os.Bundle
import com.example.calendar.base.BaseActivity
import com.example.calendar.ui.main.MainFragment
import com.example.calendar.ui.navigation.NavigationFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }


    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment is NavigationFragment) {
            fragment.onBackPressed()
            return
        }
        super.onBackPressed()
    }
}