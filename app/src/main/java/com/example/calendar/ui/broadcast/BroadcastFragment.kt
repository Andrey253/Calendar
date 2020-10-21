package com.example.calendar.ui.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calendar.R
import com.example.calendar.base.BaseFragment
import kotlinx.android.synthetic.main.broadcast_fragment.*

class BroadcastFragment : BaseFragment() {

    companion object {
        fun newInstance() = BroadcastFragment()
    }

    private val receiver = TickReceiver(::updateUi)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.broadcast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        startButton.setOnClickListener {
            context?.registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
            countVIew.text = "BroadcastReceiver зарегистрирован"
        }

        stopButton.setOnClickListener {
            context?.unregisterReceiver(receiver)
            countVIew.text = "BroadcastReceiver закрыт"
        }
    }

    override fun onStop() {
        super.onStop()
        try {
            context?.unregisterReceiver(receiver)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun updateUi(count: Int) {
        countVIew.text = "Получено сообщение: $count"
    }

    class TickReceiver(private val onUpdate: (Int) -> Unit) : BroadcastReceiver() {
        private var count = 0

        override fun onReceive(p0: Context?, p1: Intent?) {
            onUpdate(count++)
        }
    }
}