package com.example.calendar.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("Activity lifecycle: ${this::class.simpleName} onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Timber.d("Activity lifecycle: ${this::class.simpleName} onStart")
        super.onStart()
    }

    override fun onResume() {
        Timber.d("Activity lifecycle: ${this::class.simpleName} onResume")
        super.onResume()
    }

    override fun onPause() {
        Timber.d("Activity lifecycle: ${this::class.simpleName} onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.d("Activity lifecycle: ${this::class.simpleName} onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.d("Activity lifecycle: ${this::class.simpleName} onDestroy")
        super.onDestroy()
    }
}