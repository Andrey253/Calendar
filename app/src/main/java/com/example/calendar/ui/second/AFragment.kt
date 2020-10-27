package com.example.calendar.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.calendar.ExampleService
import com.example.calendar.R
import com.example.calendar.base.BaseFragment
import com.example.calendar.ui.navigation.NavigationFragment
import com.example.calendar.ui.service.ServiceFragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.a_activity.*
import kotlinx.android.synthetic.main.a_activity.serviceButton
import kotlinx.android.synthetic.main.main_fragment.*


class AFragment : BaseFragment() {

    companion object {
        const val EXTRA_TEXT = "extra_text"
        fun newInstance() = AFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.a_activity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toBactivity.setOnClickListener { openFragment(BFragment.newInstance()) }
        serviceButton.setOnClickListener { openFragment(ServiceFragment.newInstance()) }
        AView.text = "Значение из счетчика в сервисе ${ExampleService.c.toString()}"
    }
    private fun openFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun onBackPressed() {
        println("mytag this is AFragment");
        val fragment = parentFragmentManager.findFragmentById(R.id.container)

        if (fragment != null && fragment is AFragment) {
            fragment.onBackPressed()
        } else {
            parentFragmentManager.popBackStack()
        }
    }
}