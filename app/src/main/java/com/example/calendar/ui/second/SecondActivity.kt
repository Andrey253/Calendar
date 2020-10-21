package com.example.calendar.ui.second

import android.os.Bundle
import com.example.calendar.R
import com.example.calendar.base.BaseActivity
import kotlinx.android.synthetic.main.second_activity.*

class SecondActivity : BaseActivity() {

    companion object {
        const val EXTRA_TEXT = "extra_text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val text = intent.getStringExtra(EXTRA_TEXT)
        extraView.text = text
    }
}