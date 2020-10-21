package com.example.calendar

import android.app.Application
import com.example.calendar.ui.content.ContentViewModel
import com.example.calendar.ui.state.StateViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.experimental.builder.single
import timber.log.Timber

class App : Application() {

    private val calendarModule = module {
        single<CalendarManager>()
        viewModel<ContentViewModel>()
    }

    private val stateModule = module {
        viewModel { StateViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(calendarModule, stateModule)
        }
    }
}