package com.example.calendar.ui.main

import androidx.lifecycle.ViewModel
import com.example.calendar.CalendarManager

class MainViewModel(
    private val calendarManager: CalendarManager
) : ViewModel() {

    private var id: Long? = null

    fun getCalendars(): String {
        return calendarManager.getCalendars() ?: ""
    }

    fun insertEvent(): String {
        val eventId = calendarManager.insertEvent() ?: return ""
        id = eventId
        calendarManager.addReminder(eventId)
        return calendarManager.getEvent(eventId) ?: ""
    }

    fun deleteEvent(): Boolean {
        return id?.let { calendarManager.deleteEvent(it) } ?: false

    }
}