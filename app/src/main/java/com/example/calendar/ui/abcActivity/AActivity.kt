package com.example.calendar.ui.abcActivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.calendar.R
import com.example.calendar.base.BaseActivity
import com.example.calendar.base.BaseFragment
import com.example.calendar.ui.main.MainFragment
import com.example.calendar.ui.navigation.NavigationFragment
import kotlinx.android.synthetic.main.a_activity.*
import kotlinx.android.synthetic.main.navigation_fragment.*

class AActivity : BaseActivity() {

    companion object {
        const val A_TEXT = "a_activityText"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_activity)

        val text = intent.getStringExtra(A_TEXT)
        AView.text = text
    }
}