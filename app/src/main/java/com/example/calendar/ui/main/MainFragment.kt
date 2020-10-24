package com.example.calendar.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calendar.R
import com.example.calendar.base.BaseFragment
import com.example.calendar.ui.broadcast.BroadcastFragment
import com.example.calendar.ui.content.ContentFragment
import com.example.calendar.ui.navigation.NavigationFragment
import com.example.calendar.ui.second.AFragment
import com.example.calendar.ui.service.ServiceFragment
import com.example.calendar.ui.state.StateFragment
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseFragment() {

    companion object {

        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        abcButton.setOnClickListener { abcIntent() }
        implicitButton.setOnClickListener { implicitIntent() }
        navigationButton.setOnClickListener { openFragment(NavigationFragment.newInstance(0)) }
        stateButton.setOnClickListener { openFragment(StateFragment.newInstance()) }
        providerButton.setOnClickListener { openFragment(ContentFragment.newInstance()) }
        serviceButton.setOnClickListener { openFragment(ServiceFragment.newInstance()) }
        broadcastButton.setOnClickListener { openFragment(BroadcastFragment.newInstance()) }
    }

    private fun abcIntent() {
        val intent = Intent(context, AFragment::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            putExtra(AFragment.EXTRA_TEXT, "ABC intent")
        }
        startActivity(intent)
    }

    private fun implicitIntent() {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(AFragment.EXTRA_TEXT, "implicit intent")
        }

        startActivity(intent)
    }

    private fun openFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}