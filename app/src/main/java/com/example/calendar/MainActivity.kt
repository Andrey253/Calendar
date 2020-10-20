package com.example.calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.calendar.ui.broadcast.BroadcastFragment
import com.example.calendar.ui.main.MainFragment
import com.example.calendar.ui.second.SecondActivity
import com.example.calendar.ui.service.ServiceFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.main_activity)

		explicitButton.setOnClickListener { explicitIntent() }
		implicitButton.setOnClickListener { implicitIntent() }
		providerButton.setOnClickListener { openFragment(MainFragment.newInstance()) }
		serviceButton.setOnClickListener { openFragment(ServiceFragment.newInstance()) }
		broadcastButton.setOnClickListener { openFragment(BroadcastFragment.newInstance()) }
	}

	private fun explicitIntent() {
		val intent = Intent(this, SecondActivity::class.java).apply {
			addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
		}
		startActivity(intent)
	}

	private fun  implicitIntent() {
		val intent = Intent().apply {
			action = Intent.ACTION_SEND
			type = "text/plain"
		}

		if (intent.resolveActivity(packageManager) != null) {
			startActivity(intent)
		}

	}

	private fun openFragment(fragment: Fragment) {
		supportFragmentManager.beginTransaction()
			.add(R.id.container, fragment)
			.addToBackStack(null)
			.commit()
	}
}