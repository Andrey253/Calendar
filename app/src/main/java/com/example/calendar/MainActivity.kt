package com.example.calendar

import android.os.Bundle
import com.example.calendar.base.BaseActivity
import com.example.calendar.ui.navigation.NavigationFragment
import com.example.calendar.ui.second.AFragment
import com.example.calendar.ui.second.BFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AFragment.newInstance())
                .commit()
        }
    }


    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.contentFrameB)

        if (fragment is BFragment) {
            fragment.onBackPressed()
            return
        }
        super.onBackPressed()
    }
}