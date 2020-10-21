package com.example.calendar.ui.content

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.calendar.ExampleService
import com.example.calendar.R
import com.example.calendar.base.BaseFragment
import kotlinx.android.synthetic.main.content_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ContentFragment : BaseFragment() {

    companion object {

        fun newInstance() = ContentFragment()
        const val REQUEST_CODE = 123
    }

    private val viewModel: ContentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        calendarButton.setOnClickListener { message.text = viewModel.getCalendars() }
        eventButton.setOnClickListener { message.text = viewModel.insertEvent() }
        deleteButton.setOnClickListener {
            message.text = if (viewModel.deleteEvent()) {
                "удален"
            } else {
                "не удален"
            }
        }

        if (!checkCalendarPermission()) {
            askPermission(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR)
        } else {
            calendarButton.isEnabled = true
            eventButton.isEnabled = true
            deleteButton.isEnabled = true
        }

        context?.startService(Intent(context, ExampleService::class.java))
    }

    private fun askPermission(vararg permissions: String) {
        requestPermissions(permissions, REQUEST_CODE)
    }

    private fun checkCalendarPermission(): Boolean {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
			context!!,
			Manifest.permission.READ_CALENDAR
		)
    }

    override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
        calendarButton.isEnabled = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        eventButton.isEnabled = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        deleteButton.isEnabled = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
    }
}