package com.example.calendar.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.calendar.R
import com.example.calendar.base.BaseFragment

import kotlinx.android.synthetic.main.c_activity.*


class CFragment : BaseFragment() {

    companion object {
        const val EXTRA_TEXT = "extra_text"
        fun newInstance() = CFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.c_activity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toAactivity.setOnClickListener { openFragment() }

    }
    private fun openFragment() {
        childFragmentManager.beginTransaction()
                .replace(R.id.contentFrameC, AFragment.newInstance())
                .addToBackStack(null)
                .commit()
    }
}