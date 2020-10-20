package com.example.calendar.ui.service

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calendar.ExampleService
import com.example.calendar.R
import kotlinx.android.synthetic.main.service_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ServiceFragment : Fragment() {

    companion object {
        fun newInstance() = ServiceFragment()
    }

    private val viewModel: ServiceViewModel by viewModel()

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