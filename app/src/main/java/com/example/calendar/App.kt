package com.example.calendar

import android.app.Application
import com.example.calendar.ui.content.ContentViewModel
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.experimental.builder.single
import timber.log.Timber

class App : Application() {

    private val calendarModule = module {
        single<CalendarManager>()
        viewModel<ContentViewModel>()
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(calendarModule)
        }
    }
}