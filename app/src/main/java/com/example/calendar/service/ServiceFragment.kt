package com.example.calendar.service

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calendar.R
import com.example.calendar.base.BaseFragment
import kotlinx.android.synthetic.main.service_fragment.*

class ServiceFragment : BaseFragment() {

    companion object {
        fun newInstance() = ServiceFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.service_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val intent = Intent(context, ExampleService::class.java)

        serviceButton.setOnClickListener { context?.startService(intent) }
        stopButton.setOnClickListener { context?.stopService(intent) }
    }

}