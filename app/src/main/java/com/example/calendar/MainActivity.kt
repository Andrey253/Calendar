package com.example.calendar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.calendar.base.BaseActivity
import com.example.calendar.fragments.AFragment

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

    override fun onNewIntent(intent: Intent?) {
        findViewById<TextView>(R.id.a_act_textView).text = "Счетчик из сервиса = ${intent?.getIntExtra("count", 0).toString()}"
        super.onNewIntent(intent)

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

//    fun buttoncontacts(view: View) {
//                startActivity(Intent(this, Contacts::class.java))
//    }

}