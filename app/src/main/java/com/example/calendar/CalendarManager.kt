package com.example.calendar

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.provider.CalendarContract
import android.provider.CalendarContract.*
import android.util.Log
import java.util.*

class CalendarManager(context: Context) {

    private companion object {
        const val PROJECTION_ID_INDEX = 0
        const val PROJECTION_ACCOUNT_NAME_INDEX = 1
        const val PROJECTION_DISPLAY_NAME_INDEX = 2
        const val PROJECTION_OWNER_ACCOUNT_INDEX = 3
    }

    private val calendarColumns = arrayOf(
        Calendars._ID,
        Calendars.ACCOUNT_NAME,
        Calendars.CALENDAR_DISPLAY_NAME,
        Calendars.OWNER_ACCOUNT
    )

    private val eventColumns = arrayOf(
        Events._ID,
        Events.TITLE,
        Events.DESCRIPTION,
        Events.DTSTART
    )

    private val contentResolver = context.contentResolver

    fun getCalendars(): String? {

        val cursor = contentResolver.query(Calendars.CONTENT_URI, calendarColumns, null, null, null)

        val buffer = StringBuilder("ID  |  NAME  |  DISPLAY  |  OWNER\n\n")
        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getLong(PROJECTION_ID_INDEX)
                val name = it.getString(PROJECTION_ACCOUNT_NAME_INDEX)
                val displayName = it.getString(PROJECTION_DISPLAY_NAME_INDEX)
                val ownerAccount = it.getString(PROJECTION_OWNER_ACCOUNT_INDEX)
                buffer.append("$id  |  $name  |  $displayName  |  $ownerAccount \n\n")
            }
        } ?: return null

        return buffer.toString()
    }

    fun insertEvent(): Long? {
        val id = 3L
        val hour = Random().nextInt(20)
        val start = Calendar.getInstance().apply {
            set(2020, 9, 15, hour, 15)
        }.timeInMillis
        val end = Calendar.getInstance().apply {
            set(2020, 9, 15, hour + 1, 0)
        }.timeInMillis

        val timeZone = Calendar.getInstance().timeZone.displayName
        Log.d("Calendar", timeZone)
        Log.d("Calendar", Date(start).toString())
        Log.d("Calendar", Date(end).toString())

        val values = ContentValues().apply {
            put(Events.DTSTART, start)
            put(Events.DTEND, end)
            put(Events.TITLE, "Artem")
            put(Events.DESCRIPTION, "test of calculator")
            put(Events.CALENDAR_ID, id)
            put(Events.EVENT_TIMEZONE, timeZone)
        }

        val uri = contentResolver.insert(Events.CONTENT_URI, values)

        return uri?.lastPathSegment?.toLong()
    }

    fun getEvent(eventId: Long): String? {
        val selection = "(${Events._ID} = ?)"

        val cursor = contentResolver.query(Events.CONTENT_URI, eventColumns, selection, arrayOf("$eventId"), null)

        val buffer = StringBuilder("ID  |  TITLE  |  DESCRIPTION | START\n\n")
        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getLong(0)
                val title = it.getString(1)
                val description = it.getString(2)
                val start = Date(it.getLong(3)).toString()
                buffer.append("$id  |  $title  |  $description  | $start\n\n")
            }
        } ?: return null

        return buffer.toString()
    }

    fun deleteEvent(eventId: Long): Boolean {
        val uri = ContentUris.withAppendedId(Events.CONTENT_URI, eventId)
        val rows = contentResolver.delete(uri, null, null)

        return rows > 0
    }

    fun addReminder(eventId: Long) {
        val values = ContentValues().apply {
            put(Reminders.MINUTES, 13)
            put(Reminders.EVENT_ID, eventId)
            put(Reminders.METHOD, Reminders.METHOD_ALERT)
        }
        contentResolver.insert(Reminders.CONTENT_URI, values)
    }
}