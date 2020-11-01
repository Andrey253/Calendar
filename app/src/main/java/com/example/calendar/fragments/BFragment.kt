package com.example.calendar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calendar.R
import com.example.calendar.base.BaseFragment
import kotlinx.android.synthetic.main.b_fragment.*


class BFragment : BaseFragment() {

    companion object {
        const val EXTRA_TEXT = "extra_text"
        fun newInstance() = BFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.b_fragment, container, false)
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