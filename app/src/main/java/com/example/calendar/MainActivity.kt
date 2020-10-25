package com.example.calendar

import android.os.Bundle
import com.example.calendar.base.BaseActivity
import com.example.calendar.ui.second.AFragment

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

        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        println("mytag: fragment is $fragment");
        if (fragment is AFragment) {
            finish()
            return
        }
        super.onBackPressed()
    }
}