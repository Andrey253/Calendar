package com.example.calendar.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calendar.R
import com.example.calendar.base.BaseFragment
import com.example.calendar.ui.navigation.NavigationFragment
import kotlinx.android.synthetic.main.a_activity.*
import kotlinx.android.synthetic.main.a_activity.toBactivity
import kotlinx.android.synthetic.main.b_activity.*


class BFragment : BaseFragment() {

    companion object {
        const val EXTRA_TEXT = "extra_text"
        fun newInstance() = BFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.b_activity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toCactivity.setOnClickListener { openFragment() }

    }
    private fun openFragment() {
        parentFragmentManager.beginTransaction()
                .replace(R.id.container, CFragment.newInstance())
                .addToBackStack(null)
                .commit()
    }
    fun onBackPressed() {
        println("mytag this is BFragment");
        val fragment = childFragmentManager.findFragmentById(R.id.container)

        if (fragment != null && fragment is BFragment) {
            fragment.onBackPressed()
        } else {
            parentFragmentManager.popBackStack()
        }
    }
}