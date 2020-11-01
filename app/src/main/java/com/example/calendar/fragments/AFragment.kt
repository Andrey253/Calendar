package com.example.calendar.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calendar.Contacts
import com.example.calendar.MainActivity
import com.example.calendar.R
import com.example.calendar.base.BaseFragment
import com.example.calendar.service.ServiceFragment
import kotlinx.android.synthetic.main.a_fragment.*
import kotlinx.android.synthetic.main.a_fragment.serviceButton


class AFragment : BaseFragment() {

    companion object {
        const val EXTRA_TEXT = "extra_text"
        fun newInstance() = AFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.a_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toBactivity.setOnClickListener { openFragment(BFragment.newInstance()) }
        serviceButton.setOnClickListener { openFragment(ServiceFragment.newInstance()) }
        contactsbutton.setOnClickListener { start() }

    }
    private fun openFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
    }
    private fun start() {
        val intent = Intent(context, Contacts::class.java)
        context?.startActivity(intent)
    }
}